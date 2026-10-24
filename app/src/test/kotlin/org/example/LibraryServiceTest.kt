
package org.example

import org.dbu.library.model.Book
import org.dbu.library.model.Patron
import org.dbu.library.repository.InMemoryLibraryRepository
import org.dbu.library.service.BorrowResult
import org.dbu.library.service.DefaultLibraryService
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class LibraryServiceTest {

    private lateinit var service: DefaultLibraryService

@BeforeEach
fun setUp() {
    val repository = InMemoryLibraryRepository()
    service = DefaultLibraryService(repository)

    repository.addBook(Book("ISBN001", "Kotlin Basics", "John Smith", 2024))
    repository.addBook(Book("ISBN002", "Java Programming", "Mary Jones", 2023))
    repository.addBook(Book("ISBN003", "Web Development", "John Smith", 2022))
    repository.addBook(Book("ISBN004", "Database Systems", "Alice Brown", 2021))

    repository.addPatron(Patron("P001", "Sam", "sam@example.com", "0911000000"))
    repository.addPatron(Patron("P002", "Liya", "liya@example.com", "0911000001"))
}

    @Test
    fun `adding duplicate ISBN is rejected`() {
        val duplicate = Book("ISBN001", "Another Book", "Another Author", 2025)

        assertFalse(service.addBook(duplicate))
    }

    @Test
    fun `borrowing an available book succeeds`() {
        assertEquals(BorrowResult.SUCCESS, service.borrowBook("P001", "ISBN001"))
        assertFalse(service.findBook("ISBN001")!!.isAvailable)
    }

    @Test
    fun `borrowing a missing book returns BOOK_NOT_FOUND`() {
        assertEquals(BorrowResult.BOOK_NOT_FOUND, service.borrowBook("P001", "UNKNOWN"))
    }

    @Test
    fun `borrowing with a missing patron returns PATRON_NOT_FOUND`() {
        assertEquals(BorrowResult.PATRON_NOT_FOUND, service.borrowBook("UNKNOWN", "ISBN001"))
    }

    @Test
    fun `a borrowed book cannot be borrowed again`() {
        service.borrowBook("P001", "ISBN001")

        assertEquals(BorrowResult.NOT_AVAILABLE, service.borrowBook("P002", "ISBN001"))
    }

    @Test
    fun `returning a borrowed book succeeds`() {
        service.borrowBook("P001", "ISBN001")

        assertTrue(service.returnBook("P001", "ISBN001"))
        assertTrue(service.findBook("ISBN001")!!.isAvailable)
    }

    @Test
    fun `search is case insensitive`() {
        val results = service.search("kOtLiN")

        assertEquals(1, results.size)
        assertEquals("ISBN001", results.first().isbn)
    }

    @Test
    fun `patron cannot borrow more than three books`() {
        assertEquals(BorrowResult.SUCCESS, service.borrowBook("P001", "ISBN001"))
        assertEquals(BorrowResult.SUCCESS, service.borrowBook("P001", "ISBN002"))
        assertEquals(BorrowResult.SUCCESS, service.borrowBook("P001", "ISBN003"))

        assertEquals(BorrowResult.LIMIT_REACHED, service.borrowBook("P001", "ISBN004"))
    }
}
