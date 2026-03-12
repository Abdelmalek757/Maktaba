package com.ElOuedUniv.maktaba.data.repository

import com.ElOuedUniv.maktaba.data.model.Book

/**
 * Repository for managing book data
 * This follows the Repository pattern to abstract data sources
 */
class BookRepository {

    /**
     * TODO for Students (TP1 - Exercise 1):
     * Complete the book information for each book in the list below.
     * Add the following information for each book:
     * - isbn: Use a valid ISBN-13 format (e.g., "978-3-16-148410-0")
     * - nbPages: Add the actual number of pages
     *
     * Example:
     * Book(
     *     isbn = "978-0-13-468599-1",
     *     title = "Clean Code",
     *     nbPages = 464
     * )
     */
    private val booksList = listOf(
        Book(isbn = "9780136083221", title = "Clean Code", nbPages = 431),
        Book(isbn = "9780135957059", title = "The Pragmatic Programmer", nbPages = 352),
        Book(isbn = "0201633612", title = "Design Patterns", nbPages = 395),
        Book(isbn = "9780201485677", title = "Refactoring", nbPages = 464),
        Book(isbn = "9781492078005", title = "Head First Design Patterns", nbPages = 672),
        Book(isbn = "9780262046305", title = "Introduction to Algorithms Cormen", nbPages = 1312),
        Book(isbn = "9781934356364", title = "Learn to Program", nbPages = 230),
        Book(isbn = "9780262518802", title = "Algorithms Unlocked", nbPages = 240),
        Book(isbn = "9780134340012", title = "How to Solve it by Computer", nbPages = 442),
        Book(isbn = "9780735619678", title = "Code Complete", nbPages = 914)
    )

    /**
     * TODO for Students (TP1 - Exercise 2):
     * Add 5 more books to the list above.
     * Choose books related to Computer Science, Programming, or any topic you like.
     * Remember to include complete information (ISBN, title, nbPages).
     *
     * Tip: You can find ISBN numbers for books on:
     * - Google Books
     * - Amazon
     * - GoodReads
     */

    /**
     * Get all books from the repository
     * @return List of all books
     */
    fun getAllBooks(): List<Book> {
        return booksList
    }

    /**
     * Get a book by ISBN
     * @param isbn The ISBN of the book to find
     * @return The book if found, null otherwise
     */
    fun getBookByIsbn(isbn: String): Book? {
        return booksList.find { it.isbn == isbn }
    }
}
