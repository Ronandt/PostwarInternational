package com.example.international_a_am

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.international_a_am.data.Database
import com.example.international_a_am.data.Event
import com.example.international_a_am.data.EventItem
import com.example.international_a_am.data.WorldSkillsAPI
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalFoundationApi::class)
@Composable
fun EventDetailsScreen(navController: NavController, id: Int) {
    var data by remember {mutableStateOf<EventItem?>(null)}
    var imageDialog by remember { mutableStateOf<Int?>(null)}
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var event by remember {mutableStateOf<EventItem?>(null)}
    var imageList = remember {mutableStateListOf<String>()}
    LaunchedEffect(Unit) {
        event = WorldSkillsAPI().getEvents()?.filter { it.id.toInt() == id }?.get(0)
       // Database.getDb(context).eventDao().insertAll(listOf(Event()))
        WorldSkillsAPI().updateViewCount(id.toString())
        event?.otherImages?.let { imageList.addAll(it) }
        println(event?.otherImages)
        if(!Database.getDb(context)?.eventDao()?.select(id)!!) {
            Database.getDb(context)?.eventDao()?.insertAll(listOf(Event(id, "", "", "")))
        }

    }

    var pagerState = rememberPagerState(
        initialPage = imageDialog ?: 0,
        initialPageOffsetFraction = 0f
    ) {
        imageList.size
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(14.dp)){
        Back(navController = navController, modifier = Modifier.align(Alignment.Start))
        Text(text = "Event Details", fontWeight = FontWeight.SemiBold, fontSize = 22.sp)
        Spacer(modifier = Modifier.height(20.dp))
        event?.title?.let { Text(text = it, fontWeight = FontWeight.Medium, fontSize = 19.sp) }
        Chip(onClick = { /*TODO*/ }, colors = ChipDefaults.chipColors(backgroundColor = Color.Black), modifier = Modifier.padding(vertical = 8.dp)) {
            Text(text = "Views: " + (event?.viewCount?.plus(1)).toString(), color = Color.White)
        }

        Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.horizontalScroll(
            rememberScrollState())) {
            repeat(imageList.size) {
                val imagePainter = rememberImagePainter(
                    data = imageList[it],
                    builder = {
                        // Specify the placeholder GIF resource (replace with your actual placeholder)
                        placeholder(R.drawable.morecomplexpreloader)
                    }
                )

                Image(imagePainter, contentDescription = "", modifier = Modifier
                    .height(140.dp)
                    .width(120.dp)
                    .clickable {
                        imageDialog = it
                        scope.launch {
                            pagerState.scrollToPage(it)
                        }

                    }
                    .clip(RoundedCornerShape(10.dp)), contentScale = ContentScale.Crop, )
            }


        }
        Spacer(modifier = Modifier.requiredHeight(10.dp))
        
        event?.text?.let {
            Spacer(modifier = Modifier.height(14.dp))
            Text(text = "Description", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
            Text(text = it, modifier = Modifier.padding(top = 10.dp)) }

    }

    if(imageDialog != null)  {
        Dialog(onDismissRequest = { imageDialog = null}) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                HorizontalPager(state = pagerState, modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                    AsyncImage(imageList[it], contentDescription = null, modifier= Modifier
                        .size(260.dp)
                        .offset(x = 40.dp)
                        .clip(RoundedCornerShape(10.dp)), contentScale = ContentScale.Crop)
                }

            }

        }
    }

}