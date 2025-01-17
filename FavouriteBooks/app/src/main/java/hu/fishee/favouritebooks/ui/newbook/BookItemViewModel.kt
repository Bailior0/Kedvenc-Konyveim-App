package hu.fishee.favouritebooks.ui.newbook

import co.zsmb.rainbowcake.base.RainbowCakeViewModel

class BookItemViewModel : RainbowCakeViewModel<BookItemViewState>(
    Loading
) {

    fun setNewBook() {
        viewState = BookItemContent(false)
    }
}
