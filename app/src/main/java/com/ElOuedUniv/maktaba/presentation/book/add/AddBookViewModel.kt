package com.ElOuedUniv.maktaba.presentation.book.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ElOuedUniv.maktaba.data.model.Book
import com.ElOuedUniv.maktaba.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddBookViewModel @Inject constructor(
    private val bookRepository: BookRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(AddBookUiState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: AddBookUiAction) {
        when (action) {
            is AddBookUiAction.OnTitleChange -> {
                _uiState.update { it.copy(title = action.title) }
                validateInputs()
            }
            is AddBookUiAction.OnIsbnChange -> {
                _uiState.update { it.copy(isbn = action.isbn) }
                validateInputs()
            }
            is AddBookUiAction.OnPagesChange -> {
                _uiState.update { it.copy(nbPages = action.pages) }
                validateInputs()
            }
            is AddBookUiAction.OnImageChange -> {
                _uiState.update { it.copy(imageUri = action.uri) }
            }
            AddBookUiAction.OnAddClick -> {
                if (_uiState.value.isFormValid && !_uiState.value.isLoading) addBook()
            }
        }
    }

    private fun validateInputs() {
        val state = _uiState.value
        val titleError = if (state.title.isNotEmpty() && state.title.isBlank())
            "Title cannot be empty" else null
        val isbnError = if (state.isbn.isNotEmpty() &&
            (state.isbn.length != 13 || !state.isbn.all { it.isDigit() }))
            "ISBN must be exactly 13 digits" else null
        val pagesError = if (state.nbPages.isNotEmpty() &&
            (state.nbPages.toIntOrNull() == null || state.nbPages.toInt() <= 0))
            "Pages must be a positive number" else null

        val isFormValid = state.title.isNotBlank() &&
                state.isbn.length == 13 && state.isbn.all { it.isDigit() } &&
                (state.nbPages.toIntOrNull() ?: 0) > 0

        _uiState.update {
            it.copy(
                titleError = titleError,
                isbnError = isbnError,
                nbPagesError = pagesError,
                isFormValid = isFormValid
            )
        }
    }

    private fun addBook() {
        val currentState = _uiState.value
        val book = Book(
            isbn = currentState.isbn,
            title = currentState.title,
            nbPages = currentState.nbPages.toIntOrNull() ?: 0
        )
        viewModelScope.launch {
            try {
                _uiState.update { it.copy(isLoading = true) }
                bookRepository.addBook(book, currentState.imageUri)
                _uiState.update { it.copy(isSuccess = true, isLoading = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, errorMessage = e.message)
                }
            }
        }
    }
}