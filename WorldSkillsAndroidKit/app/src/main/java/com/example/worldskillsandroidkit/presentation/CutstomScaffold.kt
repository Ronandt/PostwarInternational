package com.example.worldskillsandroidkit.presentation

import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomScaffold(modifier: Modifier, content: @Composable () -> Unit, bottomBar: @Composable () -> Unit, topBar: @Composable () -> Unit) {
    var permissionState by remember {mutableStateOf<String?>(null)}

    if(permissionState != null) {
            PermissionsHandler(permission = permissionState!!, {}, {})
    }

    Scaffold(bottomBar = bottomBar, topBar = topBar) {
        Column(modifier = Modifier.padding(it)){
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
                .then(modifier)) {
                content()
            }
        }

    }

}

