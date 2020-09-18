package com.codechallenge.interfaces.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.codechallenge.model.UserDefinedLocations

@Dao
interface UserDefinedLocationsDao {

    @Query("SELECT * FROM userdefinedlocations")
    fun getAll(): LiveData<List<UserDefinedLocations>>

    @Query("SELECT * FROM userdefinedlocations")
    fun getAllData(): List<UserDefinedLocations>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(vararg todo: UserDefinedLocations)

    @Query("SELECT * FROM userdefinedlocations WHERE id = :id")
    fun get(id: Int): LiveData<UserDefinedLocations>

    @Update(onConflict = REPLACE)
    fun updateItem(mediaResponse: UserDefinedLocations)

    @Query("DELETE FROM userdefinedlocations WHERE lat = :id AND longitude = :long")
    fun delete(id: Double, long: Double)

}
