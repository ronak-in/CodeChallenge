package com.codechallenge.services

import android.content.Context
import com.codechallenge.interfaces.IStringService

class StringService(private val _context: Context) : IStringService {

    override operator fun get(key: String): String {
        val parsed = Integer.parseInt(key)
        return _context.getString(parsed)
    }

    override operator fun get(key: Int): String {
        return _context.getString(key)
    }
}