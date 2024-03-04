package com.example.worldskillsandroidkit.presentation

import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import kotlinx.coroutines.launch

@Composable
fun  PermissionsHandler(permission: String, onSuccess: () -> Unit = {}, onFailure: () -> Unit = {} ) {
    val permissionCallback = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
        if(it) onSuccess() else onFailure()
    }
    permissionCallback.launch(permission)

}


object PermissionsHandler {

    @Composable
    fun launch(permission: String, snackBarState: SnackbarHostState ) {
        val scope = rememberCoroutineScope()
        var action by remember {mutableStateOf(false    )}

        val permissionCallback = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
            if(it) {

            } else {
                scope.launch {
                   val result =  snackBarState.showSnackbar("Denying permissions may not allow the function to work", actionLabel = "Retry")
                    when(result) {
                        SnackbarResult.ActionPerformed -> {
                          action = true
                        }
                       else -> {

                       }
                    }
                }

        }

        }
        LaunchedEffect(key1 = Unit ) {
            permissionCallback.launch(permission)
        }
        LaunchedEffect(action) {
            if(action) {
                permissionCallback.launch(permission)
            }
        }



    }


}