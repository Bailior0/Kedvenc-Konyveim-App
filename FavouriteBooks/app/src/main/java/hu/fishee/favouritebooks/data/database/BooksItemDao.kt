package hu.fishee.favouritebooks.data.database

import androidx.room.*
import hu.fishee.favouritebooks.data.model.BookItem

@Dao
interface BooksItemDao {
    @Query("SELECT * FROM bookitem")
    suspend fun getAll(): List<BookItem>

    @Insert
    suspend fun insert(tripListItems: BookItem): Long

    @Update
    suspend fun update(tripListItems: BookItem)

    @Delete
    suspend fun delete(tripListItems: BookItem)
}