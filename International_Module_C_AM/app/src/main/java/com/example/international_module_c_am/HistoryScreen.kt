package com.example.international_module_c_am

import android.content.Context
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.international_module_c_am.ui.theme.CardGray
import com.example.international_module_c_am.ui.theme.DarkBlue

@Composable
fun HistoryScreen(navController: NavController, function: (Boolean) -> Unit) {
    val context = LocalContext.current
    var history = remember { mutableStateListOf<HistoryItem>()}
    LaunchedEffect(Unit) {
        WorldSkillsAPI().getHistory()?.let { history.addAll(it) }
    }
    val sharedPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("darkMode", false)
    val text1ColorAsState by animateColorAsState(targetValue = if(sharedPref) Color.White else DarkBlue)
    val text2ColorAsState by animateColorAsState(targetValue = if(sharedPref) Color.LightGray else Color.DarkGray)
    val cardBackgroundAsState by animateColorAsState(targetValue =if (sharedPref) CardGray else Color.White )

    val state = rememberLazyListState()
    LaunchedEffect(key1 = state.firstVisibleItemIndex) {
        function(state.firstVisibleItemIndex > 0)
        println(state.firstVisibleItemIndex > 0)

    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
    LazyColumn(state = state) {
        items(history?.size!!) {
            Row(modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Box(contentAlignment = Alignment.Center) {
                        Spacer(modifier = Modifier
                            .size(28.dp)
                            .border(2.dp, color = DarkBlue, shape = CircleShape))
                        if(history[it].title == "WorldSkills Competition 2022 Special Edition") {
                            Spacer(modifier = Modifier
                                .size(20.dp)
                                .background(color = DarkBlue, shape = CircleShape))
                        }
                    }

                    Divider(
                        Modifier
                            .width(2.dp)
                            .fillMaxHeight())
                    
                }
                
            
                Column {
                    history?.get(it)?.let { it1 -> Text(text = if (it1.startDate.length >= 2 && !it1.startDate[1].isDigit()) {
                        it1.startDate.substring(0, 1)
                    } else {
                        it1.startDate.substring(0, 2)
                    } + " -" + it1.endDate, fontWeight = FontWeight.SemiBold, fontSize = 20.sp, color = text2ColorAsState) }
                    history?.get(it)?.let { it1 -> Text(text = it1.title, fontWeight = FontWeight.Bold, fontSize = 24.sp, color = text1ColorAsState) }
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()){

                        Image(LoaderImage(history?.get(it)?.image!!), contentDescription = "Worldskills",modifier = Modifier
                            .size(100.dp)
                            .padding(top = 30.dp))

                        history?.get(it)?.let { it1 -> Text(text = it1.description, color = text2ColorAsState) }
                        
                    }
                }
        
            Spacer(modifier = Modifier.height(20.dp))
            }}

    }

}
}