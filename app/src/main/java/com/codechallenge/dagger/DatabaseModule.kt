package com.codechallenge.dagger

import android.content.Context
import androidx.room.Room
import com.codechallenge.repository.LocalRepository
import com.codechallenge.database.RecordDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(context: Context): RecordDatabase {
        return Room.databaseBuilder(
            context,
            RecordDatabase::class.java, "database-name"
        )
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideLocalRepository(recordDatabase: RecordDatabase) = LocalRepository(recordDatabase)

}