package com.codechallenge.ui.city

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.codechallenge.interfaces.IWeatherService
import com.codechallenge.interfaces.callbacks.RestRequestCallback
import com.codechallenge.model.WeatherRequest
import com.codechallenge.model.dto.responses.WeatherResponse
import com.codechallenge.repository.LocalRepository
import com.google.android.gms.maps.model.LatLng

class CityViewModel(
    val latLng: LatLng,
    private val mediaService: IWeatherService,
    private val localRepository: LocalRepository
) : ViewModel() {

    var error = MutableLiveData<String>()
    var loading = MutableLiveData<Boolean>()
    val weatherData: MutableLiveData<WeatherResponse> by lazy { MutableLiveData<WeatherResponse>() }

    fun init() {
        search(
            WeatherRequest(
                latLng.latitude.toString(),
                latLng.longitude.toString(),
                "fae7190d7e6433ec3a45285ffcf55c86"
            )
        )
    }


    fun search(searchRequest: WeatherRequest) {
        loading.value = true
        mediaService.search(searchRequest, object : RestRequestCallback<WeatherResponse> {
            override fun onSuccess(`object`: WeatherResponse) {
                loading.value = false
                weatherData.postValue(`object`)
            }

            override fun onFailure(errorMessage: String) {
                error.value = errorMessage
                loading.value = false
            }
        })
    }
}