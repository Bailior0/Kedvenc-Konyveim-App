package hu.fishee.favouritebooks.data.database

import androidx.room.*
import hu.fishee.favouritebooks.data.model.BookItem

@Database(entities = [BookItem::class], version = 1)
abstract class BookDatabase : RoomDatabase() {

    abstract fun booksItemDao(): BooksItemDao
}