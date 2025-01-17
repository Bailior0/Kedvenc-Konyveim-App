package hu.fishee.favouritebooks.ui.details

import hu.fishee.favouritebooks.data.model.BookItem

sealed class DetailsViewState

object Loading : DetailsViewState()

data class DetailsContent(
    val loading: Boolean = false,
    var book: BookItem? = null
) : DetailsViewState()