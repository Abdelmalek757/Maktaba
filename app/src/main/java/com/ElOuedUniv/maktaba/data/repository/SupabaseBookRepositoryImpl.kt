package com.ElOuedUniv.maktaba.data.repository

import android.content.Context
import android.net.Uri
import com.ElOuedUniv.maktaba.data.model.Book
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.postgrest
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SupabaseBookRepositoryImpl @Inject constructor(
    private val supabase: SupabaseClient,
    @ApplicationContext private val context: Context
) : BookRepository {

    override fun getAllBooks(): Flow<List<Book>> = flow {
        val books = supabase.postgrest["books"]
            .select()
            .decodeList<Book>()
        emit(books)
    }

    override suspend fun getBookByIsbn(isbn: String): Book? {
        return supabase.postgrest["books"]
            .select {
                filter { eq("isbn", isbn) }
            }
            .decodeList<Book>()
            .firstOrNull()
    }

    override suspend fun addBook(book: Book, imageUri: Uri?): Book {
        var imageUrl: String? = book.imageUrl

        if (imageUri != null) {
            val inputStream = context.contentResolver.openInputStream(imageUri)
            val bytes = inputStream?.readBytes()
            inputStream?.close()
            if (bytes != null) {
                val fileName = "book_${book.isbn}_${System.currentTimeMillis()}.jpg"
                supabase.storage["book_covers"].upload(fileName, bytes)
                imageUrl = supabase.storage["book_covers"].publicUrl(fileName)
            }
        }

        val bookWithImage = book.copy(imageUrl = imageUrl)
        supabase.postgrest["books"].insert(bookWithImage)
        return bookWithImage
    }
}