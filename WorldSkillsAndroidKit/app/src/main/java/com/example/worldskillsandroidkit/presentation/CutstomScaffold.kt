package com.example.worldskillsandroidkit.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CustomScaffold(modifier: Modifier, content: @Composable () -> Unit, bottomBar: @Composable () -> Unit, topBar: @Composable () -> Unit) {
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