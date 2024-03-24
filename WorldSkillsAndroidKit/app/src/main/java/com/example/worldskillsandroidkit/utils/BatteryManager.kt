package com.example.worldskillsandroidkit.utils

import android.content.Context
import android.os.BatteryManager

class BatteryManager(private val context: Context) {

    fun getBatteryCapacity(): Int {
        val service = context.getSystemService(BatteryManager::class.java)
        return service.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }
}