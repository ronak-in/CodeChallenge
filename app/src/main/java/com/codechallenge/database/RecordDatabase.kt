package com.codechallenge.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codechallenge.interfaces.dao.UserDefinedLocationsDao
import com.codechallenge.model.UserDefinedLocations

@Database(entities = [UserDefinedLocations::class], version = 3, exportSchema = false)
abstract class RecordDatabase: RoomDatabase() {

    abstract fun usersDefinedLocationsDao(): UserDefinedLocationsDao
}