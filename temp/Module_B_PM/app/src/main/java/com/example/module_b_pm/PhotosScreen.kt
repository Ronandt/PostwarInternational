package com.example.module_b_pm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController

@Composable
fun PhotosScreen(navController: NavController, showShadow: (Boolean) -> Unit) {
    var photos = remember { mutableStateListOf(R.drawable.one, R.drawable.two, R.drawable.three )}


    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(20.dp))
        LazyVerticalGrid(modifier = Modifier.fillMaxWidth(), columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.SpaceEvenly, verticalArrangement = Arrangement.spacedBy(20.dp)) {
            items(6) {
                var dialog by remember {mutableStateOf(false)}
                Box(modifier = Modifier.clickable {
                    showShadow(true)
                    dialog = true
                }) {
                    Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "Image", modifier = Modifier.size(300.dp).offset(x = 75.dp,), contentScale = ContentScale.Crop)
                    Column(modifier = Modifier.background(Color(0xA5000000)).align(Alignment.BottomEnd)) {
                        Text(text = "Popularity: 3000", color = Color.White)
                        Text(text = "Vists: 3500", color = Color.White)
                    }
                }
                if(dialog) {
                    Dialog(onDismissRequest = { dialog = false
                        showShadow(false)
                    }) {
                        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "Background", contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize())

                    }
                }


            }


        }

        Text(text = "1, 2, 3.....10", fontSize = 26.sp, modifier = Modifier
            .padding(10.dp)
            .align(
                Alignment.CenterHorizontally
            ))
    }

}