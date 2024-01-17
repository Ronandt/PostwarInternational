package com.example.international_module_c_am

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.example.international_module_c_am.ui.theme.DarkBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsScreen(navController: NavController) {

    //oops i forgot to unpause
    val context = LocalContext.current
    var news = remember { mutableStateListOf<NewsItem>()}
    LaunchedEffect(key1 = Unit, block =  {
        WorldSkillsAPI().getNews()?.let { news.addAll(it) }
    })


    LazyColumn(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        news?.size?.let {
            items(it) {
                Card(modifier  = Modifier
                    .fillMaxWidth()
                    .height(100.dp), onClick = {navController.navigate("newsDetail/${it}")}) {
                    Row {
                        AsyncImage(news?.get(it)?.image, contentDescription = "Logo", modifier = Modifier
                            .clip(
                                RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp)
                            )
                            .height(120.dp)
                            .width(120.dp), contentScale = ContentScale.Crop)

                        Column(modifier = Modifier
                            .padding(6.dp)
                            .fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween, ) {
                            Text(text = news[it].title, fontWeight = FontWeight.Bold, color = DarkBlue, maxLines = 2, overflow = TextOverflow.Ellipsis, fontSize = 18.sp)
                            Text(news[it].title, maxLines = 1, overflow = TextOverflow.Ellipsis, color = Color.DarkGray)
                        }
                    }

                }
            }
        }
  
    }
}