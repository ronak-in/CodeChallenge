package com.codechallenge.dagger

import android.content.Context
import com.codechallenge.interfaces.IStringService
import com.codechallenge.services.StringService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class StringServiceModule {

    @Provides
    @Singleton
    internal fun provideStringService(context: Context): IStringService {
        return StringService(context)
    }

}