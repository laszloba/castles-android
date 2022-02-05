package com.vaslufi.castles

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class CastlesApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        setupLogging()
    }

    private fun setupLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
