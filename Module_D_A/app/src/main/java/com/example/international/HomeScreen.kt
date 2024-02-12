package com.example.international

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.international.ui.theme.Aqua
import com.example.international.ui.theme.DarkBlue
import com.example.international.ui.theme.Dim
import com.example.international.ui.theme.HotPink
import com.example.international.ui.theme.MediumBlue
import com.example.international.ui.theme.VeryDarkBlue


@Composable
fun HomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(MediumBlue), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Spacer(modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .background(HotPink))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "News Center", color = Color.White)
            }

            Image(painter = painterResource(R.drawable.pattern), contentDescription = null, modifier = Modifier.fillMaxHeight(), contentScale = ContentScale.Crop)
            
        }
        Box(modifier = Modifier) {
            Image(painter = painterResource(R.drawable.module_d_ins), contentDescription = "Image", modifier = Modifier.fillMaxSize(), contentScale = ContentScale.Crop)
            Spacer(modifier = Modifier
                .fillMaxSize()
                .background(Dim))
            Column(modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterEnd)) {
                Image(painter = painterResource(R.drawable.module_d_news_image), contentDescription = "News image", modifier = Modifier
                    .height(150.dp)
                    .width(250.dp), contentScale = ContentScale.Crop)
                Spacer(modifier = Modifier.height(18.dp))
                Column(modifier = Modifier
                    .height(400.dp)
                    .width(250.dp)
                    .background(VeryDarkBlue)
                    .padding(14.dp)
                    .padding(start = 10.dp)){
                    Spacer(modifier = Modifier.height(14.dp))
                    Text(text = "Skills Change Worlds", color = Color.White,fontWeight = FontWeight.SemiBold, fontSize = 29.sp, modifier = Modifier.width(120.dp).padding(bottom = 6.dp), lineHeight = 34.sp)
                    Divider(modifier = Modifier.width(50.dp), color = Aqua, thickness = 8.dp)
                    Spacer(modifier = Modifier.height(20.dp))
                    Image(painter = painterResource(R.drawable.world), contentDescription = "World", modifier = Modifier
                        .align(Alignment.End)
                        .size(185.dp))
                }
            }

        }


    }
}
