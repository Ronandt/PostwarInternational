package com.example.international_module_c_am

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Switch
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.international_module_c_am.ui.theme.DarkBlue
import com.example.international_module_c_am.ui.theme.LightBlue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {

    var settings by remember {mutableStateOf(false)}
    val scope = rememberCoroutineScope()
    val navList = remember {listOf("News","Results", "History")}
    var pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        3
    }

    var openSheet by remember {mutableStateOf(false)}
    var sheetState = rememberModalBottomSheetState()
    LaunchedEffect(Unit) {
        sheetState.hide()
    }
    Column {
        Column(modifier = Modifier
            .fillMaxWidth()
            .height(360.dp)

            .background(
                Brush.verticalGradient(listOf(LightBlue, DarkBlue)),
                shape = RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp)
            )) {
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                IconButton(onClick = {
                    scope.launch {
                        sheetState.expand()
                        openSheet = true
                    }

                                     }, modifier = Modifier.align(Alignment.End)) {
                    Icon(Icons.Default.Settings, tint = Color.White, contentDescription = "Settings")
                }
                Image(painter = painterResource(R.drawable.worldskills), contentDescription = "Worldskills logo", modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp), contentScale = ContentScale.Fit    )
                TabRow(selectedTabIndex = pagerState.currentPage,backgroundColor = Color(0x00000000), divider = {

                }, indicator = {

                    Divider(color = Color.White,
                        modifier = Modifier
                            .requiredWidth(50.dp)
                            .tabIndicatorOffset(it[pagerState.currentPage])
                            .offset(x = -125.dp)
                    )
                }) {
                    repeat(3) {
                        Tab(selected = it == pagerState.currentPage , onClick = {
                            scope.launch {
                                pagerState.animateScrollToPage(it)
                            }
                            println("?????? $it")
                        }) {

                            Text(text = navList[it], fontWeight = FontWeight.SemiBold, color =  if(it == pagerState.currentPage) Color.White else Color(0x88FFFFFF) )
                            Spacer(modifier = Modifier.height(14.dp))
                        }
                    }
                }
            }








        }
        Spacer(modifier = Modifier.height(20.dp))
        HorizontalPager(state = pagerState) {
            when(it) {
                0 -> {
                    NewsScreen(navController = navController)
                }
                1 -> {
                    ResultsScreen(navController = navController)
                }
                2 -> {
                    HistoryScreen(navController = navController)
                }
            }
        }
        if(openSheet) {
            ModalBottomSheet(onDismissRequest = {
                scope.launch {
                    sheetState.hide()

                }
                openSheet = false
            }, sheetState = sheetState) {
                Column(modifier = Modifier) {
                    Text(text = "Settings", fontWeight = FontWeight.Bold, color = DarkBlue, fontSize = 22.sp)
                    Spacer(modifier = Modifier.height(10.dp))
                    Divider()
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = "Dark Mode")
                        androidx.compose.material3.Switch(checked = settings, onCheckedChange = {
                       settings = !settings
                        }, colors = SwitchDefaults.colors(Color.Green))
                    }
                    Divider()
                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }

    }



}