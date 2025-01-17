package hu.fishee.favouritebooks.ui.list

import co.zsmb.rainbowcake.base.RainbowCakeViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import hu.fishee.favouritebooks.data.model.BookItem
import javax.inject.Inject

@HiltViewModel
class BookListViewModel @Inject constructor(private val bookListPresenter: BookListPresenter) : RainbowCakeViewModel<BookListViewState>(
    Loading
) {

    fun add(newItem: BookItem) = execute {
        viewState = BoolListContent(loading = true)
        viewState = BoolListContent(books = bookListPresenter.add(newItem), loading = false)
    }

    fun load() = execute {
        viewState = BoolListContent(loading = true)
        viewState = BoolListContent(books = bookListPresenter.load(), loading = false)
    }

    fun edit(editedItem: BookItem) = execute {
        viewState = BoolListContent(loading = true)
        viewState = BoolListContent(books = bookListPresenter.edit(editedItem), loading = false)
    }

    fun remove(removedItem: BookItem) = execute {
        viewState = BoolListContent(books = bookListPresenter.remove(removedItem))
    }
}