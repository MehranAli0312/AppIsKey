package com.android.task.appsiakey.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppController : Application() {
    companion object {
        val TAG = "RafidBook"
    }
}