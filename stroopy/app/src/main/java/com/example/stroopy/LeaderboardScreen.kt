package com.example.stroopy

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stroopy.ui.theme.Blue
import com.example.stroopy.ui.theme.Green
import com.example.stroopy.ui.theme.LeaderBoardColor
import com.example.stroopy.ui.theme.PrimaryGreen
import com.example.stroopy.ui.theme.Yellow

@Composable
fun LeaderboardScreen(navController: NavController) {
    val context = LocalContext.current
    var saveData = remember {SaveData(context).displayUsers()}
    var leaderboard = remember {mutableStateListOf<User>()}
    var currentSession = remember {SaveData(context).retrieveCurrentSession()}
    LaunchedEffect(Unit) {
        if (saveData != null) {
            leaderboard.addAll(saveData.sortedBy { it.score }.reversed().subList(0, minOf(saveData.size, 9)))

        }
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 10.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
            Row() {
                Image(painter = painterResource(R.drawable.img), contentDescription ="Image", contentScale = ContentScale.Crop, modifier = Modifier.size(30.dp))
                Text(text = "Stroopy", fontWeight = FontWeight.Black, fontSize = 24.sp, color = Color.Black)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(context.resources.getIdentifier(currentSession?.image, "drawable", context.packageName)), contentDescription = "Conent description ")
                currentSession?.name?.let { Text(text = it) }


            }
        }
        
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20), colors = CardDefaults.cardColors(containerColor = LeaderBoardColor)) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Ranking",
                    fontWeight = FontWeight.Black,
                    color = PrimaryGreen,
                    fontSize = 40.sp, modifier = Modifier.padding(10.dp)
                )
                LazyColumn(modifier = Modifier.heightIn(max = 420.dp)) {
                    items(leaderboard.size) {
                        Column(modifier = Modifier.padding(horizontal = 6.dp)) {
                            Row(horizontalArrangement = Arrangement.spacedBy(10.dp  ), modifier = Modifier.fillMaxWidth().clickable {
                                leaderboard[it]?.image?.let { it1 -> User(name = leaderboard[it].name, image = it1, score = 0) }
                                    ?.let { it2 -> SaveData(context).insertCurrentSession(it2) }
                                navController.navigate("game")
                            }.padding(vertical = 20.dp)) {
                                Image(painter = painterResource(context.resources.getIdentifier(leaderboard[it].image, "drawable", "${context.packageName}")), contentDescription = "Image", contentScale = ContentScale.Crop, modifier = Modifier.size(50.dp))
                                Column {
                                    Text(text = leaderboard[it].name, fontWeight = FontWeight.Bold, color = Color.Black)
                                    Text(text = "Score: ${leaderboard[it].score}", fontWeight = FontWeight.Bold, color = PrimaryGreen)
                                }
                            }
                            Divider(modifier = Modifier.fillMaxWidth(), color = Color.Black)
                        }
                   
                    }
                }
                Spacer(modifier = Modifier.height(50.dp))
            }
        }
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(14.dp))
            Text(text = "Select a player from the top", color = Green, fontWeight = FontWeight.Bold)
            Text(text = "Or", color = Color.Gray)
            Row(horizontalArrangement = Arrangement.spacedBy(10.dp  )) {
                Button(onClick = { navController.navigate("game")}, colors = ButtonDefaults.buttonColors(containerColor = Blue), modifier = Modifier.width(160.dp    )) {
                    Text(text = "Play again", color = Color.White, fontWeight = FontWeight.Bold)
                }
                Button(onClick = {

                                 navController.navigate("nickname") {
                                     popUpTo("nickname")
                                 }
                }, colors = ButtonDefaults.buttonColors(containerColor = Green), modifier = Modifier.width(160.dp    )) {
                    Text(text = "New player", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }


    }
}