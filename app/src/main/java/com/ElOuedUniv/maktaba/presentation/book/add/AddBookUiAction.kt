package com.ElOuedUniv.maktaba.presentation.book.add

import android.net.Uri

sealed class AddBookUiAction {
    data class OnTitleChange(val title: String) : AddBookUiAction()
    data class OnIsbnChange(val isbn: String) : AddBookUiAction()
    data class OnPagesChange(val pages: String) : AddBookUiAction()
    data class OnImageChange(val uri: Uri?) : AddBookUiAction()
    object OnAddClick : AddBookUiAction()
}