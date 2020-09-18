package com.codechallenge.services

import com.codechallenge.R
import com.codechallenge.interfaces.IRestService
import com.codechallenge.interfaces.IStringService
import com.codechallenge.interfaces.IWeatherService
import com.codechallenge.interfaces.callbacks.RestRequestCallback
import com.codechallenge.model.WeatherRequest
import com.codechallenge.model.dto.requests.WeatherRequestDto
import com.codechallenge.model.dto.responses.WeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherService (private val _restService: IRestService, private val _stringService: IStringService) :
    IWeatherService {
    override fun search(
        searchRequest: WeatherRequest,
        callback: RestRequestCallback<WeatherResponse>
    ) {
        val searchRequestDto = WeatherRequestDto(searchRequest)
        val responseCall = _restService.getWeatherData(searchRequestDto.lat, searchRequestDto.lon, searchRequestDto.appid)
        responseCall.enqueue(object: Callback<WeatherResponse> {

            override fun onResponse(call: Call<WeatherResponse>, responseDto: Response<WeatherResponse> ) {
                if (responseDto.isSuccessful && responseDto.body() != null) {
                    callback.onSuccess(responseDto.body()!!)
                }
                else {
                    callback.onFailure(_stringService[R.string.process_request_error])
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                callback.onFailure(t.localizedMessage)
            }

        })
    }
}