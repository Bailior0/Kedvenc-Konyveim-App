package hu.fishee.favouritebooks.ui.list

sealed class BookListViewState

object Loading : BookListViewState()

data class BoolListContent(
    val loading: Boolean = false
) : BookListViewState()