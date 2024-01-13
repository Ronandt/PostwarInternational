package com.example.international

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun PhotosScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Header(text = "Photos")
        Spacer(modifier = Modifier.height(20.dp))
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(6.dp)) {
            Row(modifier = Modifier.height(400.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                LazyHorizontalGrid(rows = GridCells.Fixed(2), modifier = Modifier.height(400.dp),horizontalArrangement = Arrangement.spacedBy(6.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    items(4) {
                        Image(painter = painterResource(R.drawable.ws4), contentDescription = "Image", modifier = Modifier

                            .width(250.dp), contentScale = ContentScale.Crop)
                    }

                }
                Image(painter = painterResource(R.drawable.ws4), contentDescription = "Image", modifier = Modifier
                    .height(500.dp)
                    .width(500.dp), contentScale = ContentScale.Crop)


            }
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                Image(painter = painterResource(R.drawable.ws4), contentDescription = "Image", modifier = Modifier
                    .height(250.dp)
                    .width(500.dp), contentScale = ContentScale.Crop)
                Row(modifier = Modifier.size(500.dp, 250.dp), horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Image(painter = painterResource(R.drawable.ws4), contentDescription = "Image", modifier = Modifier
                        .height(250.dp)
                        .width(250.dp), contentScale = ContentScale.Crop)
                    Image(painter = painterResource(R.drawable.ws4), contentDescription = "Image", modifier = Modifier
                        .height(250.dp)
                        .width(250.dp), contentScale = ContentScale.Crop)
                }
            }
        }


    }


}