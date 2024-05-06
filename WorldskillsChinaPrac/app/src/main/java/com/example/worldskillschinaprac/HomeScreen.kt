package com.example.worldskillschinaprac

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.IconButton
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    val tabList = listOf("news", "results", "history")
    var pagerState = rememberPagerState() {
        tabList.size
    }
    val scope = rememberCoroutineScope()
  /*  var bottomSheet = rememberModalBottomSheetState()
    ModalBottomSheet(onDismissRequest = { /*TODO*/ }, sheetState =  bottomSheet) {
        Text(text = "Title", fontWeight = FontWeight.SemiBold, color = Color.Blue, fontSize = 20.sp)
    }*/
    var thing = rememberLazyListState()
    LaunchedEffect(key1 = thing.firstVisibleItemScrollOffset) {
        if(thing.firstVisibleItemScrollOffset > 100) {
            println("bruh")
        } else{
            println("reverse br uh")
        }
    }
    Column(modifier  = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(24.dp), brush = Brush.verticalGradient(
                    listOf(
                        Color.Blue, Color.Black
                    )
                )
            )) {
            Column(modifier = Modifier.padding(20.dp    ), verticalArrangement = Arrangement.spacedBy(10.dp)){
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End){
                    IconButton(onClick = {
                        scope.launch {

                        }
                    }) {
                        Icon(imageVector = Icons.Default.Settings, tint = Color.White, contentDescription = "Settings")
                    }

                }
                Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "tewst", contentScale = ContentScale.Crop, modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp) )
                LaunchedEffect(key1 = Unit) {
                    repeat(3) {
                        println(it)
                    }
                }


            }



        }

        TabRow(selectedTabIndex = pagerState.currentPage, divider = {}, indicator =
        {Divider(color = Color.Black, thickness = 3.dp, modifier = Modifier.tabIndicatorOffset(it[pagerState.currentPage]) )}) {
            tabList.forEachIndexed {
                    index, str ->   Tab(selected = pagerState.currentPage == index, onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(index)
                }
            }) {
                Text(text = str)
            }
            }

        }
        HorizontalPager(state = pagerState,modifier = Modifier.fillMaxSize()) {
            when(it) {
                0 -> {

                    Text(text = "Hello")
                }

                1-> {
                    Text(text = "Helloq")
                }

                2-> {
                    Text(text = "Hell2")
                }
            }

        }





    }
}