package com.example.worldskills

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import io.realm.kotlin.ext.query
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EventDetailsScreen(navController: NavController, id: String) {
    var events = remember {mutableStateListOf<Event>()}
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var realEvent by remember {mutableStateOf<Event?>(null)}
    var persistentEvent by remember {mutableStateOf<EventData?>(null)}
    LaunchedEffect(Unit) {
       persistentEvent =  RealmDb.getDb().query<EventData>("_id == $0", id).first().find()
        RealmDb.getDb().write {
           query<EventData>("_id == $0", id).first().find().apply {
                this?.viewCount = this?.viewCount?.plus(1)!!
                this?.read = true
            }
        }
     realEvent = JsonManager.getEvents(context, scope)?.get(id.toInt()) ?: Event("", "", "", "", 0, listOf(""))
    }
    val pagerState = rememberPagerState(1, pageCount = { 3 }) 
    
    var dialogClicked by remember {mutableStateOf(false)}


Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
    Text(text = "Event details", fontSize = 20.sp)
    Spacer(modifier = Modifier.height(20.dp))
    realEvent?.title?.let { Text(text = it) }
    Text(text = persistentEvent?.viewCount.toString())
    Row {
        realEvent?.otherImages?.size?.let {
            repeat(it) {
                AsyncImage(modifier = Modifier
                    .size(100.dp)
                    .clickable {
                        dialogClicked = true
                        scope.launch {
                            pagerState.scrollToPage(it)
                        }

                    },model = realEvent!!.otherImages[it], contentDescription = "Android background" )

            }
        }
    }
    realEvent?.text?.let { Text(text = it) }
}
    
    if(dialogClicked) {
        Dialog(onDismissRequest = {dialogClicked = false}) {
            HorizontalPager(state = pagerState) {
                AsyncImage(realEvent?.otherImages?.get(it), contentDescription = "Image content description" )
            }

        }
    }
    

}