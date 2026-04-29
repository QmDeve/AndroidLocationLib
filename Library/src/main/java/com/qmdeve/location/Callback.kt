package com.qmdeve.location

import android.location.Location

interface LocationCallback {
    fun onLocation(location: Location)
}

interface LocationHandle {
    fun stop()
}