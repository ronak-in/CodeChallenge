package com.codechallenge.dagger

import com.codechallenge.interfaces.IRestService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class RestServiceModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
        return client.build()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://api.openweathermap.org/")
            .build()
    }

    @Provides
    @Singleton
    internal fun provideRestService(retrofitClient: Retrofit): IRestService {
        return retrofitClient.create(IRestService::class.java)
    }

}