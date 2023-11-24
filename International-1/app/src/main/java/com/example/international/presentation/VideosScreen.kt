package com.example.international.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import com.example.international.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideosScreen(navController: NavController) {
    var comment by remember {mutableStateOf("")}
    Row(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        Column(modifier = Modifier.fillMaxWidth(0.7f)) {
            AndroidView(factory = {
                PlayerView(it)
            }, modifier = Modifier
                .fillMaxWidth()
                .height(400.dp))
            Text(text = "Video title")
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                OutlinedTextField(value = comment, onValueChange = {comment = it}, modifier = Modifier.fillMaxWidth(0.8f), placeholder = { Text(
                    text = "Publish your comment here"
                )})
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "Publish")
                }


            }
            Text(text = "12 comment")
            LazyColumn {
                items(3) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Text(text = "12-2001-20", color = Color.Gray)
                        Text(text = "Very nice work with this video")

                    }
                }
            }
        }




      LazyColumn(verticalArrangement = Arrangement.spacedBy(30.dp)) {
          item {
              Text(text = "More videos")
          }
          items(8) {
              Box(modifier = Modifier.size(250.dp, 150.dp)) {
                  Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
                  Column(modifier = Modifier.align(Alignment.BottomEnd).background(Color.Black)) {
                      Text(text = "Test")
                  }
              }
          }


        }

    }

}