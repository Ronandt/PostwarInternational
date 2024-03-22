package com.example.worldskills

import android.speech.tts.Voice
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun RecordsScreen(navController: NavController) {
    var recordingState by remember {mutableStateOf(false)}
    LaunchedEffect(Unit) {

    }
Column(modifier = Modifier
    .fillMaxSize()
    .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
    Text(text = "Records")

    Spacer(modifier = Modifier.height(10.dp))
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(10.dp)){
            Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Voice record")

            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Voice play")

            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                Text(text = "Submit ")

            }

        }

    }
    Spacer(modifier = Modifier.height(20.dp ))
   Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Audios list")
       LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp  )) {
           items(4) {
               Card(modifier = Modifier.fillMaxWidth()) {
                   Row(modifier = Modifier
                       .fillMaxWidth()
                       .padding(horizontal = 6.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                       Text(text = "audio $it")
                       IconButton(onClick = { /*TODO*/ }) {
                           Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play" )
                       }


                   }
               }
           }

       }
    }
}
}