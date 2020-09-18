package com.codechallenge.interfaces

import com.codechallenge.model.UserDefinedLocations

interface IOnCompleteData {
    fun onComplete(list: List<UserDefinedLocations>)
}