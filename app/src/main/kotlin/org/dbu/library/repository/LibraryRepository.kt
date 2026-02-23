package org.dbu.library.repository

import org.dbu.library.model.Book
import org.dbu.library.model.Patron

interface LibraryRepository {

    fun addBook(book: Book): Boolean
    fun findBook(isbn: String): Book?
    fun updateBook(book: Book)

    fun addPatron(patron: Patron): Boolean
    fun findPatron(id: String): Patron?
    fun updatePatron(patron: Patron)

    fun getAllBooks(): List<Book>
    fun deleteBook(isbn: String): Boolean

    fun getAllPatrons(): List<Patron>
    fun deletePatron(id: String): Boolean
}