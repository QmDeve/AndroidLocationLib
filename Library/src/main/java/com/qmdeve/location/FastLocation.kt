package com.qmdeve.location

import android.annotation.SuppressLint
import android.app.Application
import android.location.Location
import android.os.Handler
import android.os.Looper
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority

object FastLocation {
    private lateinit var client: FusedLocationProviderClient
    private var lastLocation: Location? = null

    fun init(application: Application) {
        client = LocationServices.getFusedLocationProviderClient(application)
    }

    /**
     * Single Location
     */
    @SuppressLint("MissingPermission")
    fun singleLocation(callback: com.qmdeve.location.LocationCallback) {
        var isCallback = false

        val locCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                if (isCallback) return
                isCallback = true

                val loc = result.lastLocation
                if (loc != null) {
                    lastLocation = loc
                    callback.onLocation(loc)
                }

                client.removeLocationUpdates(this)
            }
        }

        client.requestLocationUpdates(
            LocationRequest
                .Builder(Priority.PRIORITY_HIGH_ACCURACY, 2000L)
                .setMinUpdateIntervalMillis(1000L)
                .setMaxUpdates(1)
                .build(),
            locCallback,
            Looper.getMainLooper()
        )

        Handler(Looper.getMainLooper()).postDelayed({
            if (!isCallback) {
                isCallback = true
                client.removeLocationUpdates(locCallback)
            }
        }, 5000L)
    }

    /**
     * Start Location
     */
    @SuppressLint("MissingPermission")
    fun startLocation(callback: com.qmdeve.location.LocationCallback): LocationHandle {
        val locCallback = object : LocationCallback() {
            override fun onLocationResult(result: LocationResult) {
                val loc = result.lastLocation
                if (loc != null) {
                    lastLocation = loc
                    callback.onLocation(loc)
                }
            }
        }

        client.requestLocationUpdates(
            LocationRequest
                .Builder(Priority.PRIORITY_HIGH_ACCURACY, 2000L)
                .setMinUpdateIntervalMillis(1000L)
                .build(),
            locCallback,
            Looper.getMainLooper()
        )

        return object : LocationHandle {
            override fun stop() {
                client.removeLocationUpdates(locCallback)
            }
        }
    }
}