package com.qmdeve.location.demo

import android.app.Application
import com.qmdeve.location.FastLocation

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        FastLocation.init(this)
    }
}