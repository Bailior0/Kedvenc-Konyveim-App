package hu.fishee.favouritebooks.ui.list

import co.zsmb.rainbowcake.withIOContext
import hu.fishee.favouritebooks.data.model.BookItem
import hu.fishee.favouritebooks.domain.BooksInteractor
import javax.inject.Inject

class BookListPresenter @Inject constructor(private val booksInteractor: BooksInteractor) {

    suspend fun add(newItem: BookItem): List<BookItem> = withIOContext {
        return@withIOContext booksInteractor.add(newItem)
    }

    suspend fun load(): List<BookItem> = withIOContext {
        return@withIOContext booksInteractor.load()
    }

    suspend fun edit(editedItem: BookItem): List<BookItem> = withIOContext {
        return@withIOContext booksInteractor.edit(editedItem)
    }

    suspend fun remove(removedItem: BookItem): List<BookItem> = withIOContext {
        return@withIOContext booksInteractor.remove(removedItem)
    }
}