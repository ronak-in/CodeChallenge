package com.codechallenge.services

import android.content.Context
import android.content.SharedPreferences
import com.codechallenge.interfaces.ISharedPreferenceService
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class SharedPreferenceService(private val _context: Context) : ISharedPreferenceService {
    private val preference : SharedPreferences = _context.getSharedPreferences("Dearwolves", 0)
    private val editor : SharedPreferences.Editor = preference.edit()
    private var dateFormat: DateFormat = SimpleDateFormat("MMM d, h:mm a", Locale.getDefault())

    override fun saveLastVisit(date: Date) {
        editor.putString("last_visit", dateFormat.format(date))
        editor.apply()
    }

    override fun getLastVisit(): String {
        return preference.getString("last_visit", "")!!
    }
}