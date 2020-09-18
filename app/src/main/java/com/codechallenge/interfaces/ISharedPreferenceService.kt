package com.codechallenge.interfaces

import java.util.*

interface ISharedPreferenceService {

    fun saveLastVisit(date: Date)

    fun getLastVisit(): String
}