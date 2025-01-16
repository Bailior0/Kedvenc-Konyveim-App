package hu.fishee.favouritebooks.ui.list

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(/*private val bookListPresenter: BoolListPresenter*/) : RainbowCakeViewModel<BookListViewState>(
    Loading
) {

    fun load() = execute {
        viewState = BoolListContent(loading = true)
        viewState = BoolListContent(loading = false)
    }

    fun list() = execute {
        viewState = BoolListContent(loading = true)
        viewState = BoolListContent(loading = false)
    }
}