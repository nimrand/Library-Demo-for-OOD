package controllers

import org.scalatestplus.play._
import org.scalatestplus.play.guice._
import play.api.test._
import play.api.test.Helpers._
import business._
import scala.concurrent.Await
import scala.concurrent.duration._
import java.time.LocalDate
import scala.collection.immutable.Seq
import play.api.inject.guice._
import java.io.File

/**
 * Add your spec here.
 * You can mock out a whole application including requests, plugins etc.
 *
 * For more information, see https://www.playframework.com/documentation/latest/ScalaTestingWithScalaTest
 */
class LibraryAppBusinessLayerFacadeSpec extends PlaySpec with Injecting {
  new File("temp.sqlite").delete()
  
  implicit lazy val app = new GuiceApplicationBuilder().configure(
        "slick.dbs.default.db.url" -> "jdbc:sqlite:temp.sqlite"
      ).build
  
  val api = inject[LibraryAppBusinessLayerFacade]
      
  var authorID = AuthorID(0)
  var publisherID = PublisherID(0)
  var bookID = BookID(0)
  var memberID = LibraryMemberID(0)
  var loanID = BookLoanID(0)
      
  "LibraryAppBusinessLayerFacade " should {
    "should create a new author" in {
      authorID = Await.result(api.registerAuthor(PersonName("Joshua", "", "Bloch", "", Seq())), 5000 millis)
      val authors = Await.result(api.getAuthors(), 5000 millis)
      authors.size mustBe 1
      authors(0).id mustBe authorID
      authors(0).name mustBe PersonName("Joshua", "", "Bloch", "", Seq())
    }
    "should create a new publisher" in {
      publisherID = Await.result(api.registerPublisher("Addison-Wesley Professional"), 5000 millis)
      val publishers = Await.result(api.getPublishers(), 5000 millis)
      publishers.size mustBe 1
      publishers(0).id mustBe publisherID
      publishers(0).name mustBe "Addison-Wesley Professional"
    }
    "create a new book" in {
      bookID = Await.result(
            api.registerBook(
                EditBookDTO("Effective Jav", authorID, LocalDate.of(2017, 12, 18), "Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. This Jolt award-winning classic has now been thoroughly updated to take full advantage of the latest language and library features. The support in modern Java for multiple paradigms increases the need for specific best-practices advice, and this book delivers.", ISBN.parse("978-0134685991"), publisherID, BigDecimal("31.67"), Seq("Java", "technology", "programming"), CallNumber(BigDecimal("800.21"), s"JB1"))), 5000 millis)
      val book = Await.result(api.getBook(bookID), 5000 millis)
      book.title mustBe "Effective Jav"
      book.author.id mustBe authorID
      book.author.name mustBe PersonName("Joshua", "", "Bloch", "", Seq())
      book.publicationDate mustBe LocalDate.of(2017, 12, 18)
      book.description mustBe "Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. This Jolt award-winning classic has now been thoroughly updated to take full advantage of the latest language and library features. The support in modern Java for multiple paradigms increases the need for specific best-practices advice, and this book delivers."
      book.isbn mustBe ISBN.parse("978-0134685991")
      book.publisher.id mustBe publisherID
      book.publisher.name mustBe "Addison-Wesley Professional"
      book.price mustBe BigDecimal("31.67")
      book.keywords mustBe Seq("Java", "technology", "programming")
      book.callNumber mustBe CallNumber(BigDecimal("800.21"), s"JB1")
    }
    "not allow duplicate call numbers" in {
      assertThrows[DuplicateCallNumberException]{
        Await.result(
            api.registerBook(
                EditBookDTO("Effective Java", authorID, LocalDate.of(2017, 12, 18), "Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. This Jolt award-winning classic has now been thoroughly updated to take full advantage of the latest language and library features. The support in modern Java for multiple paradigms increases the need for specific best-practices advice, and this book delivers.", ISBN.parse("978-0134685991"), publisherID, BigDecimal("31.67"), Seq("Java", "technology", "programming"), CallNumber(BigDecimal("800.21"), s"JB1"))), 5000 millis)
      }
    }
    "edit an existing book" in {
      Await.ready(api.editBook(bookID, EditBookDTO("Effective Java", authorID, LocalDate.of(2017, 12, 18), "Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. This Jolt award-winning classic has now been thoroughly updated to take full advantage of the latest language and library features. The support in modern Java for multiple paradigms increases the need for specific best-practices advice, and this book delivers.", ISBN.parse("978-0134685991"), publisherID, BigDecimal("31.67"), Seq("Java", "technology", "programming"), CallNumber(BigDecimal("800.21"), s"JB1"))), 5000 millis)
      val book = Await.result(api.getBook(bookID), 5000 millis)
      book.title mustBe "Effective Java"
    }
    "create a new library member" in {
      memberID = Await.result(api.registerLibraryMember(EditLibraryMemberDTO(PersonName("Kevin", "Patrick", "Yancey", "Jr", Seq()), LocalDate.of(2018, 1, 1))), 5000 millis)
      val members = Await.result(api.getLibraryMembers(), 5000 millis)
      members.size mustBe 1
      members(0).name mustBe PersonName("Kevin", "Patrick", "Yancey", "Jr", Seq())
      members(0).joinedDate mustBe LocalDate.of(2018, 1, 1)
    }
    "search books" in {
      val alanAuthorID = Await.result(api.registerAuthor(PersonName("Alan", "T", "Norman", "", Seq())), 5000 millis)
      val amazonID = Await.result(api.registerPublisher("Amazon Digital Services"), 5000 millis)
      Await.result(
            api.registerBook(
                EditBookDTO("Blockchain Technology Explained: The Ultimate Beginner’s Guide About Blockchain Wallet, Mining, Bitcoin, Ethereum, Litecoin, Zcash, Monero, Ripple, Dash, IOTA and Smart Contracts", alanAuthorID, LocalDate.of(2017, 12, 12), "Instead of talking about investing, this book will focus on how blockchain technology works and how it might be used in the future. Topics", ISBN.parseAndConvert("1981522026"), amazonID, BigDecimal("0.99"), Seq("Bitcoin", "blockchain", "technology"), CallNumber(BigDecimal("805.51"), s"AN1"))), 5000 millis)
      val results = Await.result(api.searchBooks(Seq("technology"), BookSearchSort.relevance), 5000 millis)
      results.size mustBe 2
      val results2 = Await.result(api.searchBooks(Seq("Java"), BookSearchSort.relevance), 5000 millis)
      results2.size mustBe 1
    }
    "perform an advanced book search" in {
      val results = Await.result(api.searchBooks(AdvancedBookSearchQuery("", "Alan", "", "", "", ""), BookSearchSort.relevance), 5000 millis)
      results.size mustBe 1
    }
    "record book as lost" in {
      Await.result(api.reportBookLost(bookID), 5000 millis)
      val book = Await.result(api.getBook(bookID), 5000 millis)
      book.status mustBe BookStatus.Lost
    }
    "not be disposable while lost" in {
      assertThrows[BusinessException]{
        Await.result(api.disposeBook(bookID), 5000 millis)
      }
    }
    "not loan the book when lost" in {
      assertThrows[BusinessException]{
        Await.result(api.loanBook(bookID, LoanBookDTO(memberID, LocalDate.of(2018, 1, 1), LocalDate.of(2018, 2, 1))), 5000 millis)
      }
    }
    "record book as found" in {
      Await.result(api.reportBookFound(bookID), 5000 millis)
      val book = Await.result(api.getBook(bookID), 5000 millis)
      book.status mustBe BookStatus.Available
    }
    "not loan a book before a member joined" in {
      assertThrows[BusinessException]{
        Await.result(api.loanBook(bookID, LoanBookDTO(memberID, LocalDate.of(2017, 12, 15), LocalDate.of(2018, 2, 1))), 5000 millis)
      }
    }
    "not allow the due date to be before the loaned date" in {
      assertThrows[BusinessException]{
        Await.result(api.loanBook(bookID, LoanBookDTO(memberID, LocalDate.of(2018, 1, 15), LocalDate.of(2018, 1, 1))), 5000 millis)
      }
    }
    "loan books" in {
      loanID = Await.result(api.loanBook(bookID, LoanBookDTO(memberID, LocalDate.of(2018, 1, 1), LocalDate.of(2018, 2, 1))), 5000 millis)
      val loans = Await.result(api.getBookLoanHistory(bookID), 5000 millis)
      loans.size mustBe 1
      loans(0).member.id mustBe memberID
      loans(0).loanedDate mustBe LocalDate.of(2018, 1, 1)
      loans(0).dueDate mustBe LocalDate.of(2018, 2, 1)
      loans(0).returnedDate mustBe None
    }
    "not loan books already loaned" in {
      val memberID2 = Await.result(api.registerLibraryMember(EditLibraryMemberDTO(PersonName("Blake", "", "Courtney", "", Seq()), LocalDate.of(2018, 1, 1))), 5000 millis)
      assertThrows[BusinessException]{
        Await.result(api.loanBook(bookID, LoanBookDTO(memberID2, LocalDate.of(2017, 1, 2), LocalDate.of(2018, 2, 1))), 5000 millis)
      }
    }
    "not allow more than 5 books to be borrowed" in {
      for(i <- 2 to 5) {
        val bookID2 = Await.result(
            api.registerBook(
                EditBookDTO("Effective Jav", authorID, LocalDate.of(2017, 12, 18), "Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. This Jolt award-winning classic has now been thoroughly updated to take full advantage of the latest language and library features. The support in modern Java for multiple paradigms increases the need for specific best-practices advice, and this book delivers.", ISBN.parse("978-0134685991"), publisherID, BigDecimal("31.67"), Seq("Java", "technology", "programming"), CallNumber(BigDecimal("800.21"), s"JB$i"))), 5000 millis)
        Await.result(api.loanBook(bookID2, LoanBookDTO(memberID, LocalDate.of(2018, 1, 1), LocalDate.of(2018, 2, 1))), 5000 millis)
      }
      val bookID2 = Await.result(
            api.registerBook(
                EditBookDTO("Effective Jav", authorID, LocalDate.of(2017, 12, 18), "Java has changed dramatically since the previous edition of Effective Java was published shortly after the release of Java 6. This Jolt award-winning classic has now been thoroughly updated to take full advantage of the latest language and library features. The support in modern Java for multiple paradigms increases the need for specific best-practices advice, and this book delivers.", ISBN.parse("978-0134685991"), publisherID, BigDecimal("31.67"), Seq("Java", "technology", "programming"), CallNumber(BigDecimal("800.21"), s"JB6"))), 5000 millis)
      assertThrows[BusinessException]{
        Await.result(api.loanBook(bookID2, LoanBookDTO(memberID, LocalDate.of(2018, 1, 1), LocalDate.of(2018, 2, 1))), 5000 millis)
      }
    }
    "not allow the returned date to be before the loaned date" in {
      assertThrows[BusinessException]{
        Await.result(api.reportBookReturned(bookID, LocalDate.of(2017, 12, 15)), 5000 millis)
      }
    }
    "record the returned date" in {
      Await.result(api.reportBookReturned(bookID, LocalDate.of(2018, 1, 15)), 5000 millis)
      val loans = Await.result(api.getBookLoanHistory(bookID), 5000 millis)
      loans(0).returnedDate mustBe Some(LocalDate.of(2018, 1, 15))
    }
    "not record the returned date when the book is not checked out" in {
      assertThrows[BusinessException]{
        Await.result(api.reportBookReturned(bookID, LocalDate.of(2018, 1, 15)), 5000 millis)
      }
    }
    "not loan a book before its returned date" in {
      assertThrows[BusinessException]{
        Await.result(api.loanBook(bookID, LoanBookDTO(memberID, LocalDate.of(2018, 1, 14), LocalDate.of(2018, 2, 1))), 5000 millis)
      }
    }
    "loan a book a second time" in {
      Await.result(api.loanBook(bookID, LoanBookDTO(memberID, LocalDate.of(2018, 2, 1), LocalDate.of(2018, 2, 15))), 5000 millis)
    }
    "not dispose loaned books" in {
      assertThrows[BusinessException]{
        Await.result(api.disposeBook(bookID), 5000 millis)
      }
    }
    "allow books lost while loaned out" in {
      Await.result(api.reportBookLost(bookID), 5000 millis)
      val book = Await.result(api.getBook(bookID), 5000 millis)
      book.status mustBe BookStatus.Lost
    }
    "require lost loaned books report the returned date" in {
      assertThrows[BusinessException]{
        Await.result(api.reportBookFound(bookID), 5000 millis)
      }
    }
    "update status when lost loaned books are returned" in {
      Await.result(api.reportBookReturned(bookID, LocalDate.of(2018, 3, 1)), 5000 millis)
      val book = Await.result(api.getBook(bookID), 5000 millis)
      book.status mustBe BookStatus.Available
    }
    "dispose books" in {
      Await.result(api.disposeBook(bookID), 5000 millis)
      val book = Await.result(api.getBook(bookID), 5000 millis)
      book.status mustBe BookStatus.Disposed
    }
  }
}
