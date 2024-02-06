package com.example.international_module_c_am

import android.content.Context
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.international_module_c_am.ui.theme.CardGray
import com.example.international_module_c_am.ui.theme.DarkBlue
import com.example.international_module_c_am.ui.theme.DarkGray

@Composable
fun NewsDetailScreen(navController: NavController, toInt: Int) {
    val context = LocalContext.current
    var thumbsUpState by remember {mutableStateOf(false)}
    var news = remember {mutableStateListOf<NewsItem>()}
    var items by remember {mutableStateOf(NewsItem("", "", "", ""))}
    LaunchedEffect(key1 = Unit, block =  {
        WorldSkillsAPI().getNews()?.let { news.addAll(it) }
        items =  news[toInt]
    })

    val sharedPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("darkMode", false)
    val text1ColorAsState by animateColorAsState(targetValue = if(sharedPref) Color.White else DarkBlue)
    val text2ColorAsState by animateColorAsState(targetValue = if(sharedPref) Color.LightGray else Color.DarkGray)
    val cardBackgroundAsState by animateColorAsState(targetValue =if (sharedPref) CardGray else Color.White )
    val  background by animateColorAsState(targetValue = if(sharedPref) DarkGray else Color.White)

    Column(modifier  = Modifier.background(background)
        .fillMaxSize()
        .padding(20.dp)
        .verticalScroll(rememberScrollState())) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon( Icons.Default.KeyboardArrowLeft, contentDescription = "Go back", tint = text1ColorAsState, modifier = Modifier.size(30.dp))


            }
            Text(text = "News", fontWeight = FontWeight.SemiBold, color = text2ColorAsState)
        }

        Text(text = "${items?.title}", color = text1ColorAsState, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "${items?.summary}", fontWeight = FontWeight.SemiBold, fontSize = 16.sp, color = text2ColorAsState )
        Image(LoaderImage(items?.image!!),  contentDescription ="Skills", modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 20.dp).height(200.dp)
        .clip(
            RoundedCornerShape(20.dp)
        ), contentScale = ContentScale.Crop)


        items?.description?.let { Text(fontSize = 16.sp,text = it, color = text2ColorAsState) }
        Spacer(modifier = Modifier.weight(1f))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            IconButton(onClick = { thumbsUpState = !thumbsUpState}) {
                if(thumbsUpState) { Icon(Icons.Default.ThumbUp, contentDescription = "Like", tint = text1ColorAsState)

                } else {
                    Icon(painter = painterResource(R.drawable.baseline_thumb_up_off_alt_24), contentDescription = "Like", tint = text1ColorAsState)
                }


            }
        }

    }
}