package database

import business._
import javax.inject.{ Inject, Singleton }
import play.api.db.slick.DatabaseConfigProvider
import slick.jdbc.JdbcProfile
import scala.concurrent.{ Future, ExecutionContext }
import scala.collection.immutable._
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Singleton
class DbRepository @Inject() (dbSchema: DbSchema)(implicit ec: ExecutionContext) {
  import dbSchema.dbConfig._
  import profile.api._
  import dbSchema._
  
  implicit val localDateOrdering: Ordering[LocalDate] = Ordering.by(_.toEpochDay)
  
  implicit class ExtendedEditBookDTO(book : EditBookDTO) {
    def fields =
      (book.title, book.isbn, book.authorID, book.price, book.keywords, book.description, book.callNumber, book.publicationDate, book.publisherID)
  
    def insertFields =
      (book.title, book.isbn, book.authorID, book.price, book.keywords, book.description, book.callNumber, book.publicationDate, book.publisherID, BookStatus.Available)
  }
  
  implicit class ExtendedPersonName(name : PersonName) {
    def fields =
      (name.firstName, name.middleName, name.lastName, name.suffixName, name.titles)
  }
  
  class UnitOfWork[T](private val dbAction : DBIOAction[T, NoStream, Effect.All]) {
    def map[U](f : T => U) =
      UnitOfWork(dbAction.map(f))
    
    def flatMap[U](f : T => UnitOfWork[U]) : UnitOfWork[U] =
      UnitOfWork(dbAction.map(f).flatMap(_.dbAction))
    
    def execute() =
      db.run{ dbAction.transactionally }
  }
  
  object UnitOfWork {
    def apply[T](dbAction : DBIOAction[T, NoStream, Effect.All]) =
      new UnitOfWork(dbAction)
  }
  
  def createPublisher(name : String) : UnitOfWork[PublisherID] =
    UnitOfWork {
      publishers.map(p => p.name) returning dbSchema.publishers.map(_.id) into ((name, id) => id) += (name)
    }
  
  def getPublishers() : UnitOfWork[Seq[Publisher]] =
    UnitOfWork {
      publishers.result.map(_.to[Seq])
    }
  
  def createBook(book : EditBookDTO) : UnitOfWork[BookID] =
    UnitOfWork {
      (books.map(p => (p.title, p.isbn, p.authorID, p.price, p.keywords, p.description, p.callNumber, p.publicationDate, p.publisherID, p.statusCode))
          returning books.map(_.id)
          into ((fields, id) => id)
      ) += book.insertFields
    }
  
  def getBook(bookID : BookID) : UnitOfWork[Book] =
    UnitOfWork {
      books.filter(_.id === bookID).result.head.flatMap{ bookRecord =>
        (authors.filter(_.id === bookRecord.authorID) join personNames on (_.nameID === _.id)).result.head.flatMap{ authorRecord =>
          publishers.filter(_.id === bookRecord.publisherID).result.head.map{ publisher =>
            bookRecord.toBook(new Author(authorRecord._1._1, authorRecord._2._2), publisher)
          }
        }
      }
    }
  
  def editBook(bookID : BookID, book : EditBookDTO) : UnitOfWork[Unit] =
    UnitOfWork {
      books.filter(_.id === bookID).map(p => (p.title, p.isbn, p.authorID, p.price, p.keywords, p.description, p.callNumber, p.publicationDate, p.publisherID)).update(book.fields).map(_ => ())
    }
  
  def getBookStatus(bookID : BookID) : UnitOfWork[BookStatus] =
    UnitOfWork {
      books.filter(_.id === bookID).map(_.statusCode).result.head
    }
  
  def setBookStatus(bookID : BookID, status : BookStatus) : UnitOfWork[Unit] =
    UnitOfWork {
      books.filter(_.id === bookID).map(_.statusCode).update(status).map(_ => ())
    }
  
  def getLibraryMembers() : UnitOfWork[Seq[LibraryMember]] =
    UnitOfWork {
      (libraryMembers join personNames on (_.nameID === _.id)).result.map(_.map(fields => new LibraryMember(fields._1._1, fields._2._2, fields._1._3)).to[Seq])
    }
  
  def getLibraryMember(memberID : LibraryMemberID) : UnitOfWork[LibraryMember] =
    UnitOfWork {
      (libraryMembers.filter(_.id === memberID) join personNames on (_.nameID === _.id)).result.head.map(fields => new LibraryMember(fields._1._1, fields._2._2, fields._1._3))
    }
  
  private def searchBooksByTerm(searchTerm : String) : DBIOAction[Seq[BookListing], NoStream, Effect.All] = {
    val likeExpression = s"%$searchTerm%"
    books.filter(b => b.title.like(likeExpression) || b.keywords.asColumnOf[String].like(likeExpression) || b.description.like(likeExpression) || b.isbn.asColumnOf[String].like(likeExpression) || b.callNumber.like(likeExpression)).map(_.id).result.flatMap { firstMatchingBookSet =>
      publishers.filter(p => p.name.like(likeExpression)).map(_.id).result.flatMap { matchingPublisherIDs => 
        books.filter(b => b.publisherID.inSet(matchingPublisherIDs)).map(_.id).result.flatMap { secondMatchingBookSet =>
          val bookIDs = Set(firstMatchingBookSet ++ secondMatchingBookSet : _*)
          (for {
            book <- books.filter(_.id.inSet(bookIDs))
            author <- authors if book.authorID === author.id
            name <- personNames if author.nameID === name.id
          } yield (book, name)).result.map(_.to[Seq].map{ case (book, (nameID, name)) => book.toBookListing(new Author(book.authorID, name)) })
          //books.filter(_.id.inSet(bookIDs)).join(authors).on(_.authorID === _.id).join(personNames).on{ case ((book, author), name) => author.nameID === name.id }.result.map(_.to[Seq].map(_.toBookListing))
        }
      }
    }
  }
  
  def searchBooks(searchTerms : Seq[String]) : UnitOfWork[Seq[BookListing]] =
    UnitOfWork {
      DBIO.sequence(for(searchTerm <- searchTerms) yield searchBooksByTerm(searchTerm)).map { resultSets =>
        val allBooks = resultSets.flatten.groupBy(_.bookID).mapValues(_.head).values
        val relevance = resultSets.flatten.groupBy(_.bookID).mapValues(_.size)
        allBooks.to[Seq].sortBy(book => relevance(book.bookID) * -1)
      }
    }
  
  def createBookLoan(bookID : BookID, loan : LoanBookDTO) : UnitOfWork[BookLoanID] =
    UnitOfWork {
      val fields = (bookID, loan.memberID, loan.loanedDate, loan.dueDate)
      (bookLoans.map(l => (l.bookID, l.memberID, l.loanedDate, l.dueDate))
        returning bookLoans.map(_.id)
        into ((name, id) => BookLoanID(id))
      ) += fields
    }
  
  def getBookLoans(bookID : BookID) : UnitOfWork[Seq[BookLoan]] =
    UnitOfWork {
      (bookLoans.filter(_.bookID === bookID) join libraryMembers on (_.memberID === _.id) join personNames on (_._2.nameID === _.id)).result }.map(
        _.to[Seq].map(fields => new BookLoan(BookLoanID(fields._1._1._1), fields._1._1._2, new LibraryMember(fields._1._1._3, fields._2._2, fields._1._2._3), fields._1._1._4, fields._1._1._5, fields._1._1._6)).sortBy(_.loanedDate).reverse
    )
  
  def getLibraryMemberLoans(libraryMemberID : LibraryMemberID) : UnitOfWork[Seq[BookLoan]] =
    UnitOfWork {
      (bookLoans.filter(_.memberID === libraryMemberID) join libraryMembers on (_.memberID === _.id) join personNames on (_._2.nameID === _.id)).result }.map(
        _.to[Seq].map(fields => new BookLoan(BookLoanID(fields._1._1._1), fields._1._1._2, new LibraryMember(fields._1._1._3, fields._2._2, fields._1._2._3), fields._1._1._4, fields._1._1._5, fields._1._1._6)).sortBy(_.loanedDate).reverse
    )
  
  def setReturnedDate(bookLoanID : BookLoanID, returnedDate : LocalDate) : UnitOfWork[Unit] =
    UnitOfWork {
      bookLoans.filter(_.id === bookLoanID.asInt).map(_.returnedDate).update(Some(returnedDate)).map(_ => ())
    }
  
  def getBookByCallNumber(callNumber : String) : UnitOfWork[Option[Book]] =
    UnitOfWork {
      books.filter(_.callNumber === callNumber).result.headOption.flatMap{
        case Some(bookRecord) =>
          (authors.filter(_.id === bookRecord.authorID) join personNames on (_.nameID === _.id)).result.head.flatMap{ authorRecord =>
            publishers.filter(_.id === bookRecord.publisherID).result.head.map{ publisher =>
              Some(bookRecord.toBook(new Author(authorRecord._1._1, authorRecord._2._2), publisher))
            }
          }
        case None =>
          slick.dbio.SuccessAction(None)
      }
    }
  
  private def insertPersonName(name : PersonName) =
    (personNames.map(n => (n.firstName, n.middleName, n.lastName, n.suffixName, n.titles))
        returning personNames.map(_.id)
        into ((fields, id) => id)
    ) += name.fields
  
  def createLibraryMember(libraryMember : EditLibraryMemberDTO) : UnitOfWork[LibraryMemberID] =
    UnitOfWork {
      insertPersonName(libraryMember.name).flatMap{ nameID =>
        val memberColumns = (nameID, libraryMember.joinedDate)
        (libraryMembers.map(m => (m.nameID, m.joinedDate))
            returning libraryMembers.map(_.id)
            into ((fields, id) => id)
        ) += memberColumns
      }
    }
  
  def createAuthor(name : PersonName) : UnitOfWork[AuthorID] =
    UnitOfWork {
      insertPersonName(name).flatMap{ nameID =>
        (authors.map(a => (a.nameID))
            returning authors.map(_.id)
            into ((fields, id) => id)
        ) += nameID
      }
    }
  
  def getAuthors() : UnitOfWork[Seq[Author]] =
    UnitOfWork {
      (authors join personNames on (_.nameID === _.id)).result.map(_.map{ case ((authorID, nameID), (_, name)) => new Author(authorID, name) }.to[Seq].sortBy(_.name.lastName))
    }
}