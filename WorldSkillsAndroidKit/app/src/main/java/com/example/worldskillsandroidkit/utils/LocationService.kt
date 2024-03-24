package com.example.worldskillsandroidkit.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import androidx.core.app.ActivityCompat

class LocationService(private val context: Context) {
    private val locationManager = context.getSystemService(LocationManager::class.java)
    fun listen(onListen: LocationListener): Boolean{

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 1000F, onListen)
        return true
    }

}