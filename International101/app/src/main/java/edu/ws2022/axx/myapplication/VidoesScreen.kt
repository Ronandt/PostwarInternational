package edu.ws2022.axx.myapplication

import android.widget.VideoView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VideosScreen(navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }
    val context = LocalContext.current
    val api = remember {API()}
    var videos = remember { mutableStateListOf<Video>()}
    LaunchedEffect(Unit) {
        videos.addAll(api.videos())
    }
    var currentVideo by remember {mutableStateOf(0)}
    Row {

        Column {
            Spacer(modifier = Modifier.height(10.dp))
            AndroidView(factory = {
                VideoView(it).apply {

                }

            }, modifier = Modifier.size(width = 900.dp  , height = 400.dp))
            Row(
                Modifier
                    .width(900.dp)
                    .background(Color.Black)) {
                Text(
                    text = "This is the titoe",
                    color = Color.White,
                    modifier = Modifier.padding(10.dp)
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(value = text, onValueChange =  {text = it}, label = { Text(text = "Enter your comment here")}, modifier = Modifier.width(800.dp   ))
                Button(onClick = { text = "" }, modifier = Modifier.width(100.dp    )) {
                    Text(text = "Submit")
                }
            }

            Text(text = "15 comments", fontSize = 26.sp)
            LazyColumn (verticalArrangement = Arrangement.spacedBy(14.dp    )){
                items(10) {

                    Text(text = "From 180.144.143.122")
                    Spacer(modifier = Modifier.height(10.dp ))
                    Text(text = "This is amazing!")
                }
            }


        }
        Column() {
            Text(text = "Other videos", modifier = Modifier.padding(10.dp  ))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp )) {
                items(10) {
                    Box(modifier = Modifier.size(width = 250.dp, height = 150.dp    )) {
                        Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription ="", modifier = Modifier.matchParentSize(), contentScale = ContentScale.Crop)
                        Column(modifier = Modifier.background(Color.Black)) {
                            Text(
                                text = "8:30",
                                color = Color.White,
                                modifier = Modifier.padding(10.dp)
                            )
                        }

                    }

                }


            }
        }


    }
}