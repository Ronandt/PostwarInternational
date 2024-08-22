package edu.ws2022.axx.myapplication

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf

@Composable
fun PhotosScreen(navController: NavController) {
    var selection by remember {mutableStateOf(0)}
    val listOfPhotos = remember {mutableStateListOf<Photo>()}
    LaunchedEffect(Unit) {

    }
Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        LazyHorizontalGrid(rows = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(10.dp    ), verticalArrangement = Arrangement.spacedBy(10.dp ) , modifier = Modifier.height(650.dp   )) {
            items(6) {
                Box(modifier = Modifier.size(350.dp)) {
                    Image(painter = painterResource(id = R.drawable.ic_launcher_background), contentDescription = "Background", modifier = Modifier
                        .matchParentSize()
                        .clickable {

                        }, contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier
                        .padding(14.dp)
                        .align(Alignment.BottomEnd)
                        .background(Color.Black)) {
                        Text(text = "Views:  3r2234", color = Color.White)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Popularity: 0102", color = Color.White)
                    }
                }
            }
        }
    }

Spacer(modifier = Modifier.height(10.dp))
    Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(6.dp)) {
        repeat(10) {
            Text(text = (it + 1).toString(), modifier = Modifier
                .clickable {
                    selection = it
                }.background(if(it == selection) Color.LightGray else Color.Transparent)
                .padding(6.dp))
        }
    }

}
}