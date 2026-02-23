package org.dbu.library.service

import org.dbu.library.model.Book
import org.dbu.library.model.Patron
import org.dbu.library.repository.LibraryRepository

class DefaultLibraryService(
    private val repository: LibraryRepository
) : LibraryService {

    override fun addBook(book: Book): Boolean {
        return repository.addBook(book)
    }

    override fun borrowBook(patronId: String, isbn: String): BorrowResult {
        val book = repository.findBook(isbn) ?: return BorrowResult.BOOK_NOT_FOUND
        val patron = repository.findPatron(patronId) ?: return BorrowResult.PATRON_NOT_FOUND

        if (!book.isAvailable) return BorrowResult.NOT_AVAILABLE
        if (patron.borrowedBooks.size >= 3) return BorrowResult.LIMIT_REACHED

        val updatedBook = book.copy(isAvailable = false)
        val updatedPatron = patron.copy(borrowedBooks = patron.borrowedBooks + isbn)

        repository.updateBook(updatedBook)
        repository.updatePatron(updatedPatron)

        return BorrowResult.SUCCESS
    }

    override fun returnBook(patronId: String, isbn: String): Boolean {
        val book = repository.findBook(isbn) ?: return false
        val patron = repository.findPatron(patronId) ?: return false

        if (book.isAvailable) return false
        if (!patron.borrowedBooks.contains(isbn)) return false

        val updatedBook = book.copy(isAvailable = true)
        val updatedPatron = patron.copy(borrowedBooks = patron.borrowedBooks - isbn)

        repository.updateBook(updatedBook)
        repository.updatePatron(updatedPatron)

        return true
    }

    override fun findBook(isbn: String): Book? = repository.findBook(isbn)

    override fun search(query: String): List<Book> {
        val books = repository.getAllBooks()
        return books.filter { 
            it.title.contains(query, ignoreCase = true) || 
            it.author.contains(query, ignoreCase = true) 
        }
    }

    override fun deleteBook(isbn: String): Boolean {
        val book = repository.findBook(isbn) ?: return false
        if (!book.isAvailable) return false // Cannot delete a borrowed book
        return repository.deleteBook(isbn)
    }

    override fun deletePatron(id: String): Boolean {
        val patron = repository.findPatron(id) ?: return false
        if (patron.borrowedBooks.isNotEmpty()) return false // Cannot delete patron with books
        return repository.deletePatron(id)
    }

    override fun getStatistics(): Map<String, Any> {
        val books = repository.getAllBooks()
        val patrons = repository.getAllPatrons()
        
        return mapOf(
            "totalBooks" to books.size,
            "availableBooks" to books.count { it.isAvailable },
            "borrowedBooks" to books.count { !it.isAvailable },
            "totalPatrons" to patrons.size
        )
    }

    override fun getAllBooks(): List<Book> = repository.getAllBooks()

    override fun getAllPatrons(): List<Patron> = repository.getAllPatrons()
}