package com.codechallenge.dagger

import android.content.Context
import com.codechallenge.interfaces.IRestService
import com.codechallenge.interfaces.ISharedPreferenceService
import com.codechallenge.interfaces.IStringService
import com.codechallenge.interfaces.IWeatherService
import com.codechallenge.repository.LocalRepository
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ContextModule::class, RestServiceModule::class, StringServiceModule::class, MediaServiceModule::class, DatabaseModule::class, SharedPreferenceModule::class])
@Singleton
interface CoreComponent {
    fun provideApplicationContext(): Context
    fun provideRestService(): IRestService
    fun provideWeatherService(): IWeatherService
    fun provideStringService(): IStringService
    fun provideLocalRepository() : LocalRepository
    fun provideSharedPreferenceService(): ISharedPreferenceService

}