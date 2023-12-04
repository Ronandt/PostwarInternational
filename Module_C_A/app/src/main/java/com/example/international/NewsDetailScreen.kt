package com.example.international

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
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
fun NewsDetailScreen(navController: NavController) {
    var like by remember {mutableStateOf(false)}
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll(rememberScrollState())) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.navigateUp()}) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "Back", tint =   Color(0xFF053B69), modifier = Modifier.size(34.dp))

            }
            Text( text =  "News",  fontWeight = FontWeight.SemiBold, color = Color.DarkGray)

        }
        Text(text = "Skills change worlds", color =  Color(0xFF053B69), fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "We've taken everything our Members shared with us in our first ever global Impact Survey and gathered impact stories that demonstrate the skills change worlds.", fontWeight = FontWeight.SemiBold, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Image(painter = painterResource(R.drawable.skills_change), contentDescription = "Skills change worlds", modifier = Modifier
            .clip(
                RoundedCornerShape(14.dp)
            )
            .fillMaxWidth(), contentScale = ContentScale.Crop,)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Confident learners, ambitious indviduals, includisive workplace,s Producvie busines. Sustainable industrieas. Economic growrth. Skills help all tthese things thrive, which si why we ned more skilled young people.")

        Spacer(modifier = Modifier.weight(1f))
        IconButton(onClick = { like = !like  }, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            if(like) {
                Icon(painterResource(id = R.drawable.baseline_thumb_up_off_alt_24), contentDescription = "Like",tint = Color(0xFF053B69), modifier = Modifier.size(34.dp))
            } else {
                Icon(painterResource(id = R.drawable.baseline_thumb_up_alt_24), contentDescription = "Like",tint = Color(0xFF053B69) , modifier = Modifier.size(34.dp))
            }

        }
    }

}