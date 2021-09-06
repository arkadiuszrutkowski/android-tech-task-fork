package com.nordpass.task.app

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.nordpass.task.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class ToDoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}