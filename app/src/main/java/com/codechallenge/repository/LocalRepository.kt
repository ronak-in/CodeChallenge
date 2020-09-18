package com.codechallenge.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.codechallenge.database.RecordDatabase
import com.codechallenge.interfaces.IOnComplete
import com.codechallenge.interfaces.IOnCompleteData
import com.codechallenge.interfaces.dao.UserDefinedLocationsDao
import com.codechallenge.model.UserDefinedLocations

class LocalRepository(recordDatabase: RecordDatabase) {

    val daoUserDefinedLocations: UserDefinedLocationsDao
    val allUsersDefinedData: LiveData<List<UserDefinedLocations>>

    init {
        daoUserDefinedLocations = recordDatabase.usersDefinedLocationsDao()
        allUsersDefinedData = daoUserDefinedLocations.getAll()
    }


    fun getUserDefinedItem(trackId: Int): LiveData<UserDefinedLocations> {
        return daoUserDefinedLocations.get(trackId)
    }

    fun getUserDefinedAllItem(onComplete: IOnCompleteData) {
        getAllLocations(daoUserDefinedLocations, onComplete).execute()
    }

    fun insertUserDefinedLocationData(
        mediaResponses: UserDefinedLocations,
        onComplete: IOnComplete
    ) {
        InsertionLocations(daoUserDefinedLocations, onComplete).execute(mediaResponses)
    }

    fun deleteUserDefinedLocationData(lat: Double, long: Double) {
        daoUserDefinedLocations.delete(lat, long)
    }

    fun updateUserDefinedLocationItem(
        mediaResponse: UserDefinedLocations,
        onComplete: IOnComplete
    ) {
        UpdateLocations(daoUserDefinedLocations, onComplete).execute(mediaResponse)
    }

    companion object {
        class InsertionLocations(
            private val daoUserDefinedLocations: UserDefinedLocationsDao,
            private val onComplete: IOnComplete
        ) : AsyncTask<UserDefinedLocations, Void, Void?>() {
            override fun doInBackground(vararg params: UserDefinedLocations?): Void? {
                params[0]?.let {
                    daoUserDefinedLocations.insertAll(it)
                }
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                onComplete.onComplete()
            }
        }

        class UpdateLocations(
            private val daoUserDefinedLocations: UserDefinedLocationsDao,
            private val onComplete: IOnComplete
        ) : AsyncTask<UserDefinedLocations, Void, Void?>() {
            override fun doInBackground(vararg params: UserDefinedLocations): Void? {
                params[0].let {
                    daoUserDefinedLocations.updateItem(it)
                }
                return null
            }

            override fun onPostExecute(result: Void?) {
                super.onPostExecute(result)
                onComplete.onComplete()
            }
        }


        class getAllLocations(
            private val daoUserDefinedLocations: UserDefinedLocationsDao,
            private val onComplete: IOnCompleteData
        ) : AsyncTask<Void, List<UserDefinedLocations>, List<UserDefinedLocations>>() {
            override fun doInBackground(vararg p: Void?): List<UserDefinedLocations>? {
                return daoUserDefinedLocations.getAllData()
            }

            override fun onPostExecute(result: List<UserDefinedLocations>) {
                super.onPostExecute(result)
                onComplete.onComplete(result)
            }
        }
    }
}
