package com.example.worldskills_c_am

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun CommunitiesScreen(navController: NavController) {

Column(modifier  = Modifier
    .fillMaxSize()
    .background(Color.Black)
    .verticalScroll(rememberScrollState())) {
    Row (modifier  = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween){
        Text(modifier  = Modifier.padding(vertical = 10.dp ),text = "More like blackmagicfreaky", color = Color.White, fontWeight = FontWeight.SemiBold)
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Keybaord aroow right" , tint = Color.White)
        }
    }

    Row(modifier = Modifier.padding(10.dp)) {
        LazyHorizontalGrid(rows = GridCells.Fixed(2), horizontalArrangement = Arrangement.spacedBy(10.dp   ), verticalArrangement = Arrangement.spacedBy(10.dp  ), modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)) {
            items(4) {
                Column(modifier = Modifier
                    .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(10.dp))
                    .padding(10.dp)
                    .width(300.dp)) {
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(10.dp )) {
                            Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "launcherbackground", modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape), contentScale = ContentScale.Crop)
                            Column {
                                Text(text = "nononoyes", fontWeight = FontWeight.SemiBold, color = Color.White, fontSize = 20.sp)
                                Text(text = "4.2m members", color = Color.LightGray)

                            }
                        }

                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Join", fontWeight = FontWeight.SemiBold)
                        }
                    }



                    Row {
                        Text(text = "Experience hte emotional rollercoaster of near-disaster turned success", color = Color.LightGray)
                    }
                    }

            }

        }

    }
    Spacer(modifier = Modifier.height(20.dp))
Text(
    text = "Community Spotlights",
    color = Color.White,
    fontWeight = FontWeight.SemiBold,
    fontSize = 20.sp
)
    Spacer(modifier = Modifier.height(10.dp))
    Column(verticalArrangement = Arrangement.Center,modifier = Modifier
        .fillMaxWidth()
        .border(width = 1.dp, color = Color.DarkGray, shape = RoundedCornerShape(10.dp))
        .padding(10.dp)) {
        Divider(color = Color.DarkGray)
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = null, modifier = Modifier
                .fillMaxWidth(1f)
                .height(280.dp)
                .clip(
                    RoundedCornerShape(10.dp)
                ), contentScale = ContentScale.Crop,  )
        }

        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Row(modifier = Modifier, horizontalArrangement = Arrangement.spacedBy(10.dp )) {
                Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "launcherbackground", modifier = Modifier
                    .size(50.dp)
                    .clip(CircleShape), contentScale = ContentScale.Crop)
                Column {
                    Text(text = "nononoyes", fontWeight = FontWeight.SemiBold, color = Color.White, fontSize = 20.sp)
                    Text(text = "4.2m members", color = Color.LightGray)

                }
            }

            Button(onClick = { /*TODO*/ }) {
                Text(text = "Join", fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
}