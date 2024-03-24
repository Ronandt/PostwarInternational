package com.example.worldskillsandroidkit.utils

import android.content.Context
import android.hardware.camera2.CameraManager

class FlashlightManager(private val context: Context) {
    fun enableFLashlight() {
        val sysService = context.getSystemService(CameraManager::class.java)
        var cameraId: String? = null
        cameraId = sysService.cameraIdList[1]
        sysService.setTorchMode(cameraId, true)
    }
}