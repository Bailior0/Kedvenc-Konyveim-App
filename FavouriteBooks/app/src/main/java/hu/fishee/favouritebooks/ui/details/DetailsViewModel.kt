package hu.fishee.favouritebooks.ui.details

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.fishee.favouritebooks.data.model.BookItem
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor() : RainbowCakeViewModel<DetailsViewState>(
    Loading
) {
    fun setBook(book: BookItem) = execute {
        viewState = DetailsContent(false, book)
    }
}