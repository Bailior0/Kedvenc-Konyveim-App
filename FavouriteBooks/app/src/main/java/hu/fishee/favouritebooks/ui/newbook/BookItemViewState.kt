package hu.fishee.favouritebooks.ui.newbook

sealed class BookItemViewState

object Loading : BookItemViewState()

data class BookItemContent(
    var isLoading: Boolean = true
) : BookItemViewState()
