package com.example.worldskillssingapore

import android.content.Context
import android.location.LocationListener
import android.location.LocationManager

class LocationManagerer(private val context: Context) {
    fun getLocation() {
        val locationListener = LocationListener{

        }
        var locationManger=  context.getSystemService(LocationManager::class.java    )
        locationManger.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 1000F, locationListener)

    }

}