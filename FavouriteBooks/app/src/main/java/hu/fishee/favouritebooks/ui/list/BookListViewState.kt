package hu.fishee.favouritebooks.ui.list

import hu.fishee.favouritebooks.data.model.BookItem

sealed class BookListViewState

object Loading : BookListViewState()

data class BoolListContent(
    val loading: Boolean = false,
    var books: List<BookItem> = emptyList(),
) : BookListViewState()