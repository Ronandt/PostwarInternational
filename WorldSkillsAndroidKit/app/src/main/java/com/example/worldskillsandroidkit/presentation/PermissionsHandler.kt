package com.example.worldskillsandroidkit.presentation

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable

@Composable
fun PermissionsHandler(permission: String, onSuccess: () -> Unit = {}, onFailure: () -> Unit = {} ) {
    val permissionCallback = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
        if(it) onSuccess() else onFailure()
    }
    permissionCallback.launch(permission)

}