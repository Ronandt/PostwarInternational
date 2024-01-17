package com.example.international5

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun VideosScreen(navController: NavController) {
    var commentState by remember {mutableStateOf("")}
    val context = LocalContext.current
    var comments = remember {mutableStateListOf<Comment>()}
    var scope = rememberCoroutineScope()
    var change by remember {mutableStateOf(false)}
    LaunchedEffect(change) {
        var commentDao = db.getDb(context).commentDao()
        comments.clear()
        comments.addAll(commentDao.selectAll())
    }
    Row(modifier = Modifier.fillMaxSize()) {
        Column {
            AndroidView(factory = {
                PlayerView(it).apply {

                }
            }, modifier = Modifier
                .height(400.dp)
                .width(850.dp))
            Text(text = "Video title", fontSize = 24.sp)
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.width(850.dp), verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(value = commentState, onValueChange = {commentState = it}, label =  {Text("Type in your commetn here")}, modifier = Modifier.width(700.dp))
                Button(onClick = {
                    scope.launch {
                        db.getDb(context).commentDao().insert(Comment(commentState, "192.168.73.31"))
                    }
                    change = !change
                }) {
                    Text(text = "Publish")
                }
            }
            LazyColumn {
                item {
                    Text(text = comments.size.toString() + "Comments" , fontSize = 20.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    
                }
                items(comments.size) {
                    Column(modifier = Modifier.width(850.dp), verticalArrangement = Arrangement.spacedBy(5.dp)) {
                        Text(text = comments[it].user, color = Color.Gray)
                        Text(text = comments[it].description, fontSize = 16.sp)
                        Divider()
                    }
                }
            }
        }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.padding(10.dp)) {
            item {
                Text(text = "More videos")
                Spacer(modifier = Modifier.height(10.dp))
                
             
            }
            items(10) {
                Box(modifier = Modifier.size(250.dp, 150.dp)) {
                    Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "Background", modifier = Modifier.matchParentSize(), contentScale = ContentScale.Crop)
                    Text(text = "1:30", modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .background(Color(0xAA0000000)))

                }
            }
        }
    }
}