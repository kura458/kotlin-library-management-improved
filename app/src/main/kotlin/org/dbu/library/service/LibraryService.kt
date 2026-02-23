package org.dbu.library.service

import org.dbu.library.model.Book
import org.dbu.library.model.Patron

interface LibraryService {

    fun addBook(book: Book): Boolean

    fun borrowBook(patronId: String, isbn: String): BorrowResult

    fun returnBook(patronId: String, isbn: String): Boolean
    fun findBook(isbn: String): Book?
    fun search(query: String): List<Book>
    fun deleteBook(isbn: String): Boolean
    fun deletePatron(id: String): Boolean
    fun getStatistics(): Map<String, Any>
    fun getAllBooks(): List<Book>
    fun getAllPatrons(): List<Patron>
}