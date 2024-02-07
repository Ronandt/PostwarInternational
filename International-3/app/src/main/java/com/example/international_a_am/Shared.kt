package com.example.international_a_am

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.international_a_am.data.EventItem

@Composable
fun Back(navController: NavController, modifier: Modifier) {
    IconButton(onClick = { navController.navigateUp()}, modifier = modifier) {
        Icon(painterResource(R.drawable.baseline_arrow_back_24), contentDescription = "Go back" )
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun EventCard(event: EventItem, navController: NavController, modifier: Modifier = Modifier) {
    Card(onClick = {

        navController.navigate("eventDetails/${event.id}")


    }, colors = CardDefaults.cardColors(Color.White), elevation = CardDefaults.cardElevation(defaultElevation = 0.dp), shape = RoundedCornerShape(10.dp    )) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Column {
                val imagePainter = rememberImagePainter(
                    data = event.image,
                    builder = {
                        // Specify the placeholder GIF resource (replace with your actual placeholder)
                        placeholder(R.drawable.morecomplexpreloader)
                    }
                )
                Image(painter = imagePainter, contentDescription = "image", modifier = Modifier
                    .size(100.dp)
                    .clip(
                        RoundedCornerShape(10.dp)
                    ), contentScale = ContentScale.Crop)

            }
            Column() {
                Text(event.title, fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                Text(text = event.text, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Chip(onClick = { /*TODO*/ }, colors =  ChipDefaults.chipColors(backgroundColor = Color.Black)) {
                    event.status?.let { Text(text = it, color = Color.White, fontWeight = FontWeight.Medium) }
                }

            }


        }
    }
}