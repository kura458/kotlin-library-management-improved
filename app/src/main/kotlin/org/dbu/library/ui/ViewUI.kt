package org.dbu.library.ui

import org.dbu.library.service.LibraryService

fun viewPatronsWithBooks(service: LibraryService) {
    println("\n==================================================")
    println("        PATRONS AND BORROWED BOOKS REPORT")
    println("==================================================")
    
    val patrons = service.getAllPatrons()
    
    if (patrons.isEmpty()) {
        println("| No patrons registered in the system.          |")
    } else {
        patrons.forEach { patron ->
            println("ID: ${patron.id}")
            println("Name: ${patron.name}")
            println("Contact: ${patron.email} | ${patron.phone}")
            
            val borrowed = patron.borrowedBooks.map { isbn ->
                service.findBook(isbn)?.title ?: "Unknown ($isbn)"
            }
            
            if (borrowed.isEmpty()) {
                println("Borrowed Books: [None]")
            } else {
                println("Borrowed Books:")
                borrowed.forEachIndexed { index, title ->
                    println("  ${index + 1}. $title")
                }
            }
            println("--------------------------------------------------")
        }
    }
    println("==================================================\n")
}
