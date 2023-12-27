package com.pepperkim.baseframework

import android.app.Application
import com.pepperkim.baseframework.repo.DataStoreModule
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class App : Application() {

    companion object {
        private lateinit var app: App
        fun getInstance() : App = app
    }

    private lateinit var dataStore : DataStoreModule

    override fun onCreate() {
        super.onCreate()

        app = this

        object : Timber.DebugTree() {
            override fun createStackElementTag(element: StackTraceElement): String? {
                return super.createStackElementTag(element)
                    ?.let { "[${Thread.currentThread().name}] $it" }
            }
        }.let { Timber.plant(it) }


        dataStore = DataStoreModule(this)
    }

    fun getDataStore() : DataStoreModule = dataStore
}