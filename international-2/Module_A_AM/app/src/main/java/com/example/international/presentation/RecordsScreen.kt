package com.example.international.presentation

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun RecordsScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Records", fontSize =24.sp  )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedCard(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Voice Record")
            }
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Voice Play")
            }
            Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.End)) {
                Text(text = "Submit")
            }
        }
        Spacer(modifier = Modifier.height(40.dp))
        Text(text = "Audio list", fontSize = 20.sp)
        LazyColumn {
         items(5) {
             Row(modifier = Modifier
                 .height(40.dp)
                 .fillMaxWidth()
                 .clickable {

                 }, horizontalArrangement = Arrangement.SpaceBetween) {
                 Text(text = "Audio $it")
                 Icon( Icons.Default.PlayArrow, contentDescription = "Play" )
             }
         }
        }
    }
}