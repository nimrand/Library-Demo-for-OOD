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
class DbRepository @Inject() (dbSchema: DbSchema)(implicit ec: ExecutionContext) extends Repository {
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
  
  implicit class ExtendedString(string : String) {
    def toLikeExpression =
      s"%${string.trim}%"
  }
  
  implicit class ExtendedBookSearchResultSeq(books : Seq[SearchResult[BookListing]]) {
    def sort(sort : BookSearchSort) =
      sort match {
        case BookSearchSort.relevance => books.sortBy(_.relevance * -1)
        case BookSearchSort.title => books.sortBy(_.item.title)
        case BookSearchSort.authorFirstName => books.sortBy(_.item.author.name.firstName)
        case BookSearchSort.authorLastName => books.sortBy(_.item.author.name.lastName)
      }
  }
  
  class DbUnitOfWork[T](private val dbAction : DBIOAction[T, NoStream, Effect.All]) extends UnitOfWork[T, DbUnitOfWork] {
    def map[U](f : T => U) =
      UnitOfWork(dbAction.map(f))
    
    def flatMap[U](f : T => DbUnitOfWork[U]) : DbUnitOfWork[U] =
      UnitOfWork(dbAction.map(f).flatMap(_.dbAction))
    
    def execute() =
      db.run{ dbAction.transactionally }
  }
  
  object UnitOfWork {
    def apply[T](dbAction : DBIOAction[T, NoStream, Effect.All]) =
      new DbUnitOfWork(dbAction)
  }
  
  type W[T] = DbUnitOfWork[T]
  
  def createPublisher(name : String) : DbUnitOfWork[PublisherID] =
    UnitOfWork {
      publishers.map(p => p.name) returning dbSchema.publishers.map(_.id) into ((name, id) => id) += (name)
    }
  
  def getPublishers() : DbUnitOfWork[Seq[Publisher]] =
    UnitOfWork {
      publishers.result.map(_.sortBy(_.name).to[Seq])
    }
  
  def createBook(book : EditBookDTO) : DbUnitOfWork[BookID] =
    UnitOfWork {
      (books.map(p => (p.properties, p.status))
          returning books.map(_.id)
          into ((fields, id) => id)
      ) += (book, BookStatus.Available)
    }
  
  def getBook(bookID : BookID) : DbUnitOfWork[Book] =
    UnitOfWork {
      (for {
        book <- books if book.id === bookID
        author <- book.author
        authorName <- author.name
        publisher <- book.publisher
      } yield (book, authorName.name, publisher.name)).result.head.map{ case ((bookID, bookProperties, bookStatus), authorName, publisherName) => new Book(bookID, bookProperties, authorName, publisherName, bookStatus) }
    }
  
  def editBook(bookID : BookID, book : EditBookDTO) : DbUnitOfWork[Unit] =
    UnitOfWork {
      books.filter(_.id === bookID).map(_.properties).update(book).map(_ => ())
    }
  
  def getBookStatus(bookID : BookID) : DbUnitOfWork[BookStatus] =
    UnitOfWork {
      books.filter(_.id === bookID).map(_.status).result.head
    }
  
  def setBookStatus(bookID : BookID, status : BookStatus) : DbUnitOfWork[Unit] =
    UnitOfWork {
      books.filter(_.id === bookID).map(_.status).update(status).map(_ => ())
    }
  
  def getLibraryMembers() : DbUnitOfWork[Seq[LibraryMember]] =
    UnitOfWork {
      (libraryMembers join personNames on (_.nameID === _.id)).result.map(_.map(fields => new LibraryMember(fields._1._1, fields._2._2, fields._1._3)).sortBy(_.id.asInt).to[Seq])
    }
  
  def getLibraryMember(memberID : LibraryMemberID) : DbUnitOfWork[LibraryMember] =
    UnitOfWork {
      (libraryMembers.filter(_.id === memberID) join personNames on (_.nameID === _.id)).result.head.map(fields => new LibraryMember(fields._1._1, fields._2._2, fields._1._3))
    }
  
  private def searchBooksByTerm(searchTerm : String) : DBIOAction[Seq[BookListing], NoStream, Effect.All] = {
    val likeExpression = searchTerm.toLikeExpression
    
    (for {
      book <- books
      author <- book.author
      authorName <- author.name
      publisher <- book.publisher if book.title.like(likeExpression) || book.keywords.asColumnOf[String].like(likeExpression) || book.description.like(likeExpression) || book.isbn.asColumnOf[String].like(likeExpression) || book.callNumber.asColumnOf[String].like(likeExpression) || publisher.name.like(likeExpression) || authorName.firstName.like(likeExpression) || authorName.lastName.like(likeExpression)
    } yield (book, authorName.name)).result.map(_.to[Seq].map{ case ((bookID, bookProperties, bookStatus), authorName) => new BookListing(bookID, bookProperties, authorName, bookStatus) })
  }
  
  def searchBooks(searchTerms : Seq[String], sort : BookSearchSort) : DbUnitOfWork[Seq[SearchResult[BookListing]]] =
    UnitOfWork {
      DBIO.sequence(for(searchTerm <- searchTerms) yield searchBooksByTerm(searchTerm)).map { resultSets =>
        val allBooks = resultSets.flatten.groupBy(_.bookID).mapValues(_.head).values
        val relevance = resultSets.flatten.groupBy(_.bookID).mapValues(_.size.toFloat / searchTerms.size)
        allBooks.to[Seq].map(book => new SearchResult(relevance(book.bookID), book)).sort(sort)
      }
    }
  
  def searchBooks(query : AdvancedBookSearchQuery, sort : BookSearchSort) : DbUnitOfWork[Seq[SearchResult[BookListing]]] =
    UnitOfWork {
      val isbnQuery = ISBN.tryParseAndConvert(query.isbn).asOption.map(_.toString).getOrElse(query.isbn.replace("""\s+""", "").replace("-", ""))
      (for {
        book <- books
        author <- book.author
        authorName <- author.name
        publisher <- book.publisher if book.title.like(query.title.toLikeExpression) && (authorName.firstName.like(query.authorName.toLikeExpression) || authorName.lastName.like(query.authorName.toLikeExpression)) && publisher.name.like(query.publisherName.toLikeExpression) && (book.description.like(query.description.toLikeExpression) || book.keywords.asColumnOf[String].like(query.description.toLikeExpression)) && book.callNumber.asColumnOf[String].like(query.callNumber.toLikeExpression) && book.isbn.asColumnOf[String].like(isbnQuery.toLikeExpression)
      } yield (book, authorName.name)).result.map(_.to[Seq].map{ case ((bookID, bookProperties, bookStatus), authorName) => new SearchResult(1.0f, new BookListing(bookID, bookProperties, authorName, bookStatus)) }.sort(sort))
    }
  
  def createBookLoan(bookID : BookID, loan : LoanBookDTO) : DbUnitOfWork[BookLoanID] =
    UnitOfWork {
      val fields = (bookID, loan.memberID, loan.loanedDate, loan.dueDate)
      (bookLoans.map(l => (l.bookID, l.memberID, l.loanedDate, l.dueDate))
        returning bookLoans.map(_.id)
        into ((name, id) => id)
      ) += fields
    }
  
  def getBookLoans(bookID : BookID) : DbUnitOfWork[Seq[BookLoan]] =
    UnitOfWork {
    (for {
      loan <- bookLoans if loan.bookID === bookID
      member <- loan.libraryMember
      memberName <- member.name
    } yield (loan.id, loan.bookID, loan.loanedDate, loan.dueDate, loan.returnedDate, member.id, member.joinedDate, memberName.name)).result.map(_.map{
      case (loanID, bookID, loanedDate, dueDate, returnedDate, memberID, memberJoinedDate, memberName) =>
        new BookLoan(loanID, bookID, new LibraryMember(memberID, memberName, memberJoinedDate), loanedDate, dueDate, returnedDate)
    }.to[Seq].sortBy(_.loanedDate).reverse)
  }
  
  def getLibraryMemberLoans(libraryMemberID : LibraryMemberID) : DbUnitOfWork[Seq[BookLoan]] =
    UnitOfWork {
      (bookLoans.filter(_.memberID === libraryMemberID) join libraryMembers on (_.memberID === _.id) join personNames on (_._2.nameID === _.id)).result }.map(
        _.to[Seq].map(fields => new BookLoan(fields._1._1._1, fields._1._1._2, new LibraryMember(fields._1._1._3, fields._2._2, fields._1._2._3), fields._1._1._4, fields._1._1._5, fields._1._1._6)).sortBy(_.loanedDate).reverse
    )
  
  def setReturnedDate(bookLoanID : BookLoanID, returnedDate : LocalDate) : DbUnitOfWork[Unit] =
    UnitOfWork {
      bookLoans.filter(_.id === bookLoanID).map(_.returnedDate).update(Some(returnedDate)).map(_ => ())
    }
  
  def getBookByCallNumber(callNumber : CallNumber) : DbUnitOfWork[Option[Book]] =
    UnitOfWork {
      (for {
        book <- books if book.callNumber === callNumber
        author <- book.author
        authorName <- author.name
        publisher <- book.publisher
      } yield (book, authorName.name, publisher.name)).result.headOption.map(_.map{
        case ((bookID, bookProperties, bookStatus), authorName, publisherName) =>
          new Book(bookID, bookProperties, authorName, publisherName, bookStatus)
      })
    }
  
  private def insertPersonName(name : PersonName) =
    (personNames.map(n => (n.firstName, n.middleName, n.lastName, n.suffixName, n.titles))
        returning personNames.map(_.id)
        into ((fields, id) => id)
    ) += name.fields
  
  def createLibraryMember(libraryMember : EditLibraryMemberDTO) : DbUnitOfWork[LibraryMemberID] =
    UnitOfWork {
      insertPersonName(libraryMember.name).flatMap{ nameID =>
        val memberColumns = (nameID, libraryMember.joinedDate)
        (libraryMembers.map(m => (m.nameID, m.joinedDate))
            returning libraryMembers.map(_.id)
            into ((fields, id) => id)
        ) += memberColumns
      }
    }
  
  def createAuthor(name : PersonName) : DbUnitOfWork[AuthorID] =
    UnitOfWork {
      insertPersonName(name).flatMap{ nameID =>
        (authors.map(a => (a.nameID))
            returning authors.map(_.id)
            into ((fields, id) => id)
        ) += nameID
      }
    }
  
  def getAuthors() : DbUnitOfWork[Seq[Author]] =
    UnitOfWork {
      (for {
        author <- authors
        name <- author.name
      } yield (author.id, name.name)).result.map(_.map{ case (authorID, name) => new Author(authorID, name) }.sortBy(_.name.firstName).to[Seq])
    }
}