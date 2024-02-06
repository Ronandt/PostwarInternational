package com.example.international_module_c_am

import android.content.Context
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.international_module_c_am.ui.theme.CardGray
import com.example.international_module_c_am.ui.theme.DarkBlue
import com.example.international_module_c_am.ui.theme.DarkGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(navController: NavController, reductionCallback: (Boolean) -> Unit) {

    //oops i forgot to unpause
    val context = LocalContext.current
    val sharedPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("darkMode", false)
    var reduce by remember {mutableStateOf(false)}
    var news = remember { mutableStateListOf<NewsItem>()}
    LaunchedEffect(key1 = Unit, block =  {
        WorldSkillsAPI().getNews()?.let { news.addAll(it) }
    })
    val state = rememberLazyListState()
    LaunchedEffect(key1 = state.firstVisibleItemIndex) {
        reductionCallback(state.firstVisibleItemIndex > 0)
        println(state.firstVisibleItemIndex > 0)

    }
    val text1ColorAsState by animateColorAsState(targetValue = if(sharedPref) Color.White else DarkBlue)
    val text2ColorAsState by animateColorAsState(targetValue = if(sharedPref) Color.LightGray else Color.DarkGray)
    val cardBackgroundAsState by animateColorAsState(targetValue =if (sharedPref) CardGray else Color.White )
    LazyColumn(state = state,modifier = Modifier
        .background(if (sharedPref) DarkGray else Color.White)
        .fillMaxWidth()
        .padding(10.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        news?.size?.let {
            items(it) {
                Card(modifier  = Modifier
                    .fillMaxWidth()
                    .height(100.dp), onClick = {navController.navigate("newsDetail/${it}")}, colors = CardDefaults.cardColors(containerColor = cardBackgroundAsState)) {
                    Row {
                        AsyncImage(news?.get(it)?.image, contentDescription = "Logo", modifier = Modifier
                            .clip(
                                RoundedCornerShape(0.dp, 0.dp)
                            )
                            .height(120.dp)
                            .width(120.dp), contentScale = ContentScale.Crop)

                        Column(modifier = Modifier
                            .padding(6.dp)
                            .fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween, ) {
                            Text(text = news[it].title, fontWeight = FontWeight.Bold, color =text1ColorAsState, maxLines = 2, overflow = TextOverflow.Ellipsis, fontSize = 18.sp)
                            Text(news[it].title, maxLines = 1, overflow = TextOverflow.Ellipsis, color =text2ColorAsState)
                        }
                    }

                }
            }
        }
  
    }
}