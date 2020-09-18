package com.codechallenge

import android.app.Application
import com.codechallenge.components.DaggerRecordComponent
import com.codechallenge.dagger.ContextModule
import com.codechallenge.components.RecordComponent
import com.codechallenge.dagger.DaggerCoreComponent

class RecordApp : Application() {

    private lateinit var _component: RecordComponent

    fun getRecordComponent(): RecordComponent {
        return _component
    }

    override fun onCreate() {
        super.onCreate()

        val coreComponent = DaggerCoreComponent
            .builder()
            .contextModule(ContextModule(applicationContext))
            .build()

        _component = DaggerRecordComponent
            .builder()
            .coreComponent(coreComponent)
            .build()

    }

}