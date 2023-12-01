package com.example.module_b_pm

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideosScreen(navController: NavController) {

    var comment by remember {mutableStateOf("")}
    val context = LocalContext.current
  Row(modifier = Modifier.fillMaxSize()) {
      Column {
          AndroidView(factory = {
              PlayerView(it)
          }, modifier = Modifier
              .fillMaxWidth(0.6f)
              .size(300.dp))
          Text(text = "Video title")
          Row {
              OutlinedTextField(value = comment, onValueChange = {comment = it}, modifier = Modifier.fillMaxWidth(0.6f))
              Spacer(modifier = Modifier.width(10.dp))
              Button(onClick = { /*TODO*/ }) {
                    Text(text = "Publish")

              }

          }
          Text(text = "12 Comments")
          LazyColumn {
              items(10) {
                  repeat(10) {
                      Row(modifier = Modifier.fillMaxWidth(0.6f)) {
                          Spacer(modifier = Modifier.height(20.dp   ))
                          Divider()
                          Text(text = "From 192.168.452.1", color = Color.Gray)
                          Text(text = "A very nice video, I like it")
                  }

                  }
              }

          }
      }
      Column {

          LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
              item {
                  Text("More videos")
              }

              items(10) {
                  Image(modifier = Modifier.requiredSize(300.dp, 140.dp),painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "")


              }

          }
      }
   }

}