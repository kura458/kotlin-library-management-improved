package org.dbu.library.ui

import org.dbu.library.model.Book
import org.dbu.library.model.Patron
import org.dbu.library.repository.LibraryRepository
import org.dbu.library.service.BorrowResult
import org.dbu.library.service.LibraryService

fun handleMenuAction(
    choice: String,
    service: LibraryService,
    repository: LibraryRepository
): Boolean {

    return when (choice) {

        "1" -> {
            addBook(service)
            true
        }

        "2" -> {
            registerPatron(repository)
            true
        }

        "3" -> {
            borrowBook(service)
            true
        }

        "4" -> {
            returnBook(service)
            true
        }

        "5" -> {
            search(service)
            true
        }

        "6" -> {
            listAllBooks(service)
            true
        }

        "7" -> {
            viewPatronsWithBooks(service)
            true
        }

        "8" -> {
            deleteBook(service)
            true
        }

        "9" -> {
            deletePatron(service)
            true
        }

        "10" -> {
            viewStatistics(service)
            true
        }

        "0" -> false

        else -> {
            println("Invalid option")
            true
        }
    }
}

fun addBook(service: LibraryService) {
    println("\n--- Add Book ---")
    print("Enter ISBN: ")
    val isbn = readln()
    print("Enter Title: ")
    val title = readln()
    print("Enter Author: ")
    val author = readln()
    print("Enter Year: ")
    val year = readln().toIntOrNull() ?: 0

    val book = Book(isbn, title, author, year)
    if (service.addBook(book)) {
        println("Book added successfully!")
    } else {
        println("Error: Book with ISBN $isbn already exists.")
    }
}

fun registerPatron(repository: LibraryRepository) {
    println("\n--- Register Patron ---")
    print("Enter Patron ID: ")
    val id = readln()
    print("Enter Name: ")
    val name = readln()
    print("Enter Email: ")
    val email = readln()
    print("Enter Phone: ")
    val phone = readln()
    
    val patron = Patron(id, name, email, phone)
    if (repository.addPatron(patron)) {
        println("Patron registered successfully!")
    } else {
        println("Error: Patron with ID $id already exists.")
    }
}

fun borrowBook(service: LibraryService) {
    println("\n--- Borrow Book ---")
    print("Enter Patron ID: ")
    val patronId = readln()
    print("Enter Book ISBN: ")
    val isbn = readln()

    val result = service.borrowBook(patronId, isbn)
    when (result) {
        BorrowResult.SUCCESS -> println("Book borrowed successfully!")
        BorrowResult.BOOK_NOT_FOUND -> println("Error: Book not found.")
        BorrowResult.PATRON_NOT_FOUND -> println("Error: Patron not found.")
        BorrowResult.NOT_AVAILABLE -> println("Error: Book is already borrowed.")
        BorrowResult.LIMIT_REACHED -> println("Error: Patron has reached the borrowing limit (3 books).")
    }
}

fun returnBook(service: LibraryService) {
    println("\n--- Return Book ---")
    print("Enter Patron ID: ")
    val patronId = readln()
    print("Enter Book ISBN: ")
    val isbn = readln()

    if (service.returnBook(patronId, isbn)) {
        println("Book returned successfully!")
    } else {
        println("Error: Could not return book. Please check the Patron ID and ISBN.")
    }
}

fun search(service: LibraryService) {
    println("\n--- Search Books ---")
    print("Enter search query (title or author): ")
    val query = readln()
    val books = service.search(query)

    if (books.isEmpty()) {
        println("No books found matching '$query'.")
    } else {
        println("\nFound ${books.size} books:")
        books.forEach { book ->
            val status = if (book.isAvailable) "Available" else "Borrowed"
            println("${book.isbn}: ${book.title} by ${book.author} (${book.year}) [$status]")
        }
    }
}

fun listAllBooks(service: LibraryService) {
    println("\n--- All Books ---")
    val books = service.getAllBooks()
    if (books.isEmpty()) {
        println("The library has no books currently.")
    } else {
        books.forEach { book ->
            val status = if (book.isAvailable) "Available" else "Borrowed"
            println("${book.isbn}: ${book.title} by ${book.author} (${book.year}) [$status]")
        }
    }
}


fun deleteBook(service: LibraryService) {
    println("\n--- Delete Book ---")
    print("Enter ISBN to delete: ")
    val isbn = readln()
    if (service.deleteBook(isbn)) {
        println("Book deleted successfully!")
    } else {
        println("Error: Could not delete book. It may not exist or is currently borrowed.")
    }
}

fun deletePatron(service: LibraryService) {
    println("\n--- Delete Patron ---")
    print("Enter Patron ID to delete: ")
    val id = readln()
    if (service.deletePatron(id)) {
        println("Patron deleted successfully!")
    } else {
        println("Error: Could not delete patron. They may not exist or still have borrowed books.")
    }
}

fun viewStatistics(service: LibraryService) {
    val stats = service.getStatistics()
    println("\n--- Library Statistics ---")
    println("Total Books: ${stats["totalBooks"]}")
    println("Available:   ${stats["availableBooks"]}")
    println("Borrowed:    ${stats["borrowedBooks"]}")
    println("Total Patrons: ${stats["totalPatrons"]}")
}