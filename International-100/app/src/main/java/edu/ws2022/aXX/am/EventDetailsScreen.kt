package edu.ws2022.aXX.am

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventDetailsScreen(navController: NavController, id: Int?) {
    val context = LocalContext.current
    val database = remember {AppDatabase.getDb(context)}
    var selectedItem by remember {mutableStateOf<Event?    >(null)}
    val scope  = rememberCoroutineScope()
    LaunchedEffect(Unit) {
        if (id != null) {
            selectedItem = database.eventDao().selectEvent(id)
            database.eventDao().updateEvent(selectedItem.apply {
                this?.viewCount?.plus(1)
                this?.status = "Read"
            }!!)
        }
    }
    var pagerState = rememberPagerState()
    var dialog by remember { mutableStateOf(false   )}
    AppColumn {
        Header(title = "Event Details")
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "${selectedItem?.title}", fontWeight = FontWeight.SemiBold, fontSize = 18.sp )
        Text(text = "View count: ${selectedItem?.viewCount?.plus(1)}")
        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {

            AsyncImage("${selectedItem?.image1}", contentDescription = "background", modifier = Modifier
                .size(80.dp)
                .clickable {
                    scope.launch {
                        pagerState.scrollToPage(0)

                    }
                    dialog = true

                }, contentScale = ContentScale.Crop )
            AsyncImage("${selectedItem?.image2}", contentDescription = "background", modifier = Modifier
                .size(80.dp)
                .clickable {
                    scope.launch {
                        pagerState.scrollToPage(1)

                    }
                    dialog = true

                }, contentScale = ContentScale.Crop )
            AsyncImage("${selectedItem?.image3}", contentDescription = "background", modifier = Modifier
                .size(80.dp)
                .clickable {
                    scope.launch {
                        pagerState.scrollToPage(2)

                    }
                    dialog = true


                }, contentScale = ContentScale.Crop )

        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "${selectedItem?.text}")

    }

    if(dialog) {
        Dialog(onDismissRequest = { dialog = false   }) {
            HorizontalPager(pageCount = 3, state =  pagerState) {
                when(it) {
                    0 -> {
                        AsyncImage("${selectedItem?.image1   }", contentDescription = "image", modifier = Modifier.size(200.dp  ) )
                    }
                    1-> {
                        AsyncImage("${selectedItem?.image2   }", contentDescription = "image", modifier = Modifier.size(200.dp  ) )
                    }
                    2 -> {
                        AsyncImage("${selectedItem?.image3   }", contentDescription = "image", modifier = Modifier.size(200.dp  ) )
                    }
                }
            }
        }

    }
}
