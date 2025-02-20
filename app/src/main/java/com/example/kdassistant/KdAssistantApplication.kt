package com.example.kdassistant

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class KdAssistantApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
