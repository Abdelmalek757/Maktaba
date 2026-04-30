package com.ElOuedUniv.maktaba.data.repository

import android.net.Uri
import com.ElOuedUniv.maktaba.data.model.Book
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.emitAll
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor() : BookRepository {

    private val _booksList = mutableListOf(
        Book(isbn = "9780132350884", title = "Clean Code", nbPages = 464,
            imageUrl = "https://covers.openlibrary.org/b/isbn/9780132350884-L.jpg"),
        Book(isbn = "9780201616224", title = "The Pragmatic Programmer", nbPages = 352,
            imageUrl = "https://covers.openlibrary.org/b/isbn/9780201616224-L.jpg"),
    )

    private val booksFlow = MutableSharedFlow<List<Book>>(replay = 1).apply {
        tryEmit(_booksList.toList())
    }

    override fun getAllBooks(): Flow<List<Book>> = flow {
        emitAll(booksFlow)
    }

    override suspend fun getBookByIsbn(isbn: String): Book? {
        return _booksList.find { it.isbn == isbn }
    }

    override suspend fun addBook(book: Book, imageUri: Uri?): Book {
        _booksList.add(book)
        booksFlow.tryEmit(_booksList.toList())
        return book
    }
}