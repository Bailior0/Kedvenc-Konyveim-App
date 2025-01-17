package hu.fishee.favouritebooks.data.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "bookitem")
data class BookItem(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "Title") var title: String = "",
    @ColumnInfo(name = "Author") var author: String = "",
    @ColumnInfo(name = "Description") var description: String = "",
    @ColumnInfo(name = "Cover") var cover: String = ""
) : Parcelable