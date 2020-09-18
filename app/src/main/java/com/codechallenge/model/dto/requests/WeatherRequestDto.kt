package com.codechallenge.model.dto.requests

import com.codechallenge.model.WeatherRequest
import com.google.gson.annotations.SerializedName

class WeatherRequestDto {
    @SerializedName("lat")
    var lat: String = ""

    @SerializedName("lon")
    var lon: String = ""

    @SerializedName("appid")
    var appid: String = ""


    /***
     * Default constructor as per GSON Requirement
     */
    constructor() {

    }

    constructor(weatherRequest: WeatherRequest) {
        this.lat = weatherRequest.lat
        this.lon = weatherRequest.lon
        this.appid = weatherRequest.appid
    }

}