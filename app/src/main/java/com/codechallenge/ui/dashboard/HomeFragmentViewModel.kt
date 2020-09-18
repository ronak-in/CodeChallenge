package com.codechallenge.ui.dashboard

import android.util.Log
import androidx.lifecycle.ViewModel
import com.codechallenge.interfaces.IOnComplete
import com.codechallenge.model.UserDefinedLocations
import com.codechallenge.repository.LocalRepository

class HomeFragmentViewModel(
    val userDefinedLocations: UserDefinedLocations,
    val localRepository: LocalRepository
) : ViewModel() {

    var TAG = javaClass.simpleName

    fun init() {

    }

    fun addMarkedLocation() {
        localRepository.insertUserDefinedLocationData(userDefinedLocations,
            object : IOnComplete {
                override fun onComplete() {
                    Log.d(TAG, "onComplete: ")
                }
            })
    }
}