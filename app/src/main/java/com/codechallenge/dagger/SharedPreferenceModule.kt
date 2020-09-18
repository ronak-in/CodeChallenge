package com.codechallenge.dagger

import android.content.Context
import com.codechallenge.interfaces.ISharedPreferenceService
import com.codechallenge.services.SharedPreferenceService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SharedPreferenceModule {

    @Provides
    @Singleton
    internal fun provideSharedPreferenceService(context: Context): ISharedPreferenceService {
        return SharedPreferenceService(context)
    }
}