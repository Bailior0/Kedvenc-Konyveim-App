package hu.fishee.favouritebooks.domain

import hu.fishee.favouritebooks.data.database.BooksItemDao
import hu.fishee.favouritebooks.data.model.BookItem
import javax.inject.Inject

class BooksInteractor @Inject constructor(private val database: BooksItemDao) {


    suspend fun load(): List<BookItem> {
        val data  = mutableListOf<BookItem>()
        data.addAll(database.getAll())
        return data
    }

    suspend fun add(newItem: BookItem): List<BookItem> {
        database.insert(newItem)
        return load()
    }

    suspend fun edit(editedItem: BookItem): List<BookItem> {
        database.update(editedItem)
        return load()
    }

    suspend fun remove(removedItem: BookItem): List<BookItem> {
        database.delete(removedItem)
        return load()
    }
}