package com.example.worldskillsandroidkit.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasicListItem(navigationOnClick: () -> Unit = {}, title: String, description: String, extraContent: @Composable () -> Unit = {}, imageLink: String? = null) {
    Card(modifier  = Modifier
        .fillMaxWidth()
        .height(100.dp), onClick = navigationOnClick, colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Row {
            if(imageLink != null) {
                Image(
                    ImageHandler(imageLink), contentDescription = "Logo", modifier = Modifier
                        .clip(
                            RoundedCornerShape(0.dp, 0.dp)
                        )
                        .height(120.dp)
                        .width(120.dp), contentScale = ContentScale.Crop)
            }


            Column(modifier = Modifier
                .padding(6.dp)
                .fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween, ) {
                Text(text = title   , fontWeight = FontWeight.Bold, color =Color.Black, maxLines = 2, overflow = TextOverflow.Ellipsis, fontSize = 18.sp)
                Text(description, maxLines = 1, overflow = TextOverflow.Ellipsis, color = Color.DarkGray)
            }
        }
        extraContent()

    }
}