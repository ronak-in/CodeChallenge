package com.codechallenge.dagger

import com.codechallenge.interfaces.IRestService
import com.codechallenge.interfaces.IStringService
import com.codechallenge.interfaces.IWeatherService
import com.codechallenge.services.WeatherService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MediaServiceModule {

    @Provides
    @Singleton
    internal fun provideWeatherService(restService: IRestService, stringService: IStringService): IWeatherService {
        return WeatherService(restService, stringService)
    }
}