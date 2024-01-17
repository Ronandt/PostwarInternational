package com.example.international_module_c_am

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.international_module_c_am.ui.theme.DarkBlue

@Composable
fun HistoryScreen(navController: NavController) {
    val context = LocalContext.current
    var history = remember { mutableStateListOf<HistoryItem>()}
    LaunchedEffect(Unit) {
        WorldSkillsAPI().getHistory()?.let { history.addAll(it) }
    }
Column(modifier = Modifier
    .fillMaxWidth()
    .padding(10.dp)) {
    LazyColumn {
        items(history?.size!!) {
            Row(modifier = Modifier.height(intrinsicSize = IntrinsicSize.Min), horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(modifier = Modifier
                        .size(28.dp)
                        .border(2.dp, color = DarkBlue, shape = CircleShape))
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
                    } + " -" + it1.endDate, fontWeight = FontWeight.SemiBold, fontSize = 20.sp  ) }
                    history?.get(it)?.let { it1 -> Text(text = it1.title, fontWeight = FontWeight.Bold, fontSize = 24.sp, color = DarkBlue) }
                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = Modifier.fillMaxWidth()){
                        AsyncImage(model = history?.get(it)?.image, contentDescription = "Worldskills",modifier = Modifier
                            .size(100.dp)
                            .padding(top = 30.dp))

                        history?.get(it)?.let { it1 -> Text(text = it1.description) }
                        
                    }
                }
        
            Spacer(modifier = Modifier.height(20.dp))
            }}

    }

}
}