package hu.fishee.favouritebooks.data.database

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseAppModule {

    @Singleton
    @Provides
    fun provideBooksDatabase( @ApplicationContext app: Context) = Room.databaseBuilder(
        app,
        BookDatabase::class.java,
        "books-list"
    ).build()

    @Singleton
    @Provides
    fun provideYourDao(db: BookDatabase) = db.booksItemDao()
}