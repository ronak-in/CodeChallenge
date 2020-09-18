package com.codechallenge.interfaces.callbacks

interface RestRequestCallback<T> {
    fun onSuccess(`object`: T)
    fun onFailure(errorMessage: String)
}