package com.example.international.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.international.R

@Composable
fun PhotosScreen(navController: NavController) {

    Column(modifier = Modifier.fillMaxSize()) {
   
        LazyVerticalGrid(columns = GridCells.Fixed(3), horizontalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterHorizontally), verticalArrangement = Arrangement.spacedBy(20.dp), modifier = Modifier.fillMaxWidth()) {
            items(6) {
                var fullScreenImage by remember { mutableStateOf(false)}
                Box(modifier = Modifier
                    .size(260.dp)
                    .clickable {
                        fullScreenImage = true
                    }) {
                    Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "", modifier = Modifier.size(260.dp), contentScale = ContentScale.Fit)
                    Column(modifier = Modifier
                        .offset(x = 150.dp, y = 215.dp)
                        .background(Color.Black)) {
                        Text(text = "Responses: 24", color = Color.White)
                        Text(text = "Votes: 24", color = Color.White)

                    }

                }

                if(fullScreenImage) {
                    Dialog(onDismissRequest = { fullScreenImage = false }) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "", modifier = Modifier.fillMaxSize())
                        }
                    }
                }

            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Text(text = "12345678810", fontSize = 30.sp)
        }
    }


}