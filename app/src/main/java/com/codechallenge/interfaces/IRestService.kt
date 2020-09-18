package com.codechallenge.interfaces

import com.codechallenge.model.dto.responses.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IRestService {

    @GET("data/2.5/forecast")
    fun getWeatherData(
        @Query("lat") term: String, @Query("lon") country: String, @Query("appid") media: String
    ): Call<WeatherResponse>
}