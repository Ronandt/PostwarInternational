package com.example.shoepanic


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.shoepanic.ui.theme.Header
import kotlinx.coroutines.delay

@Composable
fun ScanScreen(navController: NavController) {

    var dialogBox by remember {mutableStateOf(false )}
    LaunchedEffect(key1 = Unit) {
        delay(200L)
        dialogBox = true
    }
    Box(modifier = Modifier.fillMaxSize()) {
       /* AndroidView(factory = {

        })*/
        IconButton(onClick = { navController.navigateUp() },
            Modifier
                .size(50.dp)
                .background(Color.Black, shape = CircleShape)) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back", tint = Color.White)
        }

    }
    AnimatedVisibility(visible = dialogBox) {
        Dialog(onDismissRequest = { dialogBox = false }) {
            Column(modifier = Modifier
                .fillMaxWidth(0.9f)
                .height(200.dp)) {
                Header(text = "Start queueing")

            }
        }
    }
}