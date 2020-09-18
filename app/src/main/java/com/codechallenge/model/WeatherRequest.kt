package com.codechallenge.model

class WeatherRequest {

    var lat: String = ""
    var lon: String = ""
    var appid: String = ""

    constructor(lat: String, lon: String, appid: String) {
        this.lat = lat
        this.lon = lon
        this.appid = appid
    }
}