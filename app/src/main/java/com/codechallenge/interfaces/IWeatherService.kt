package com.codechallenge.interfaces

import com.codechallenge.interfaces.callbacks.RestRequestCallback
import com.codechallenge.model.WeatherRequest
import com.codechallenge.model.dto.responses.WeatherResponse

interface IWeatherService {
    fun search(
        searchRequest: WeatherRequest,
        callback: RestRequestCallback<WeatherResponse>
    )
}