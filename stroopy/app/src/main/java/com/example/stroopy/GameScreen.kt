package com.example.stroopy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stroopy.ui.theme.Blue
import com.example.stroopy.ui.theme.Green
import com.example.stroopy.ui.theme.Orange
import com.example.stroopy.ui.theme.PrimaryGreen
import com.example.stroopy.ui.theme.Purple
import com.example.stroopy.ui.theme.Red
import com.example.stroopy.ui.theme.Yellow
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun GameScreen(navController: NavController) {
    var score by remember { mutableIntStateOf(0) }
    var setOfColors = remember {listOf<Pair<String, Color>>(
        "Yellow" to Yellow,
        "Green" to Green,
        "Purple" to Purple,
        "Red" to Red    ,
        "Blue" to Blue,
        "Orange" to Orange

    ) }

    val currentDataset = remember { mutableStateListOf<Pair<String, Color>>() }
    var chosenRandomColors = remember {mutableStateListOf<Pair<String, Color>>()}
    val chosenSolution= remember {mutableStateOf<Pair<String, Color>?>(null)}
    var chosenColor = remember {mutableStateOf<Color?>(null)}
    var timer  by remember { mutableFloatStateOf(1.0f) }
    var winCondition by remember {mutableStateOf(true)}

    val context = LocalContext.current
    val saveData = remember {SaveData(context = context )}
    val currentSession = remember {saveData.retrieveCurrentSession()}
    LaunchedEffect(score) {
        while(true) {

            delay(500)
            timer -= 0.25f
            if(timer == 0.0f) {


                if(winCondition) {
                    val user = saveData.retrieveCurrentSession()
                    user?.name?.let { User(name = it, image = user.image, score = score ) }
                        ?.let { saveData.insertUser(it) }

                    delay(2000)
                    timer = 1f
                    navController.navigate("leaderboard") {
                        popUpTo("leaderboard")
                    }
                }



            }
        }
    }
    LaunchedEffect(score) {
        timer = 1f
        chosenRandomColors.clear()
        currentDataset.clear()
        currentDataset.addAll(setOfColors)
        repeat(3) {
            val randomColor = currentDataset.random()
            chosenRandomColors.add(randomColor)
            currentDataset.remove(randomColor)

        }
        chosenSolution.value = chosenRandomColors.random()
        chosenColor.value = chosenRandomColors.random().second


    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 15.dp)) {
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
        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Score: $score", fontWeight = FontWeight.Bold, color = PrimaryGreen)
            Spacer(modifier = Modifier.height(20.dp))
            Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(24), elevation = CardDefaults.cardElevation(defaultElevation = 10.dp    ), colors = CardDefaults.cardColors(containerColor = Color(
                0xFFF5F5F5
            ))) {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                    chosenSolution.value?.first?.let {
                        chosenColor.value?.let { it1 ->
                            Text(
                                text = it.uppercase(), letterSpacing = 14.sp    ,
                                fontWeight = FontWeight.Black,
                                fontSize = 40.sp,
                                modifier = Modifier.padding(10.dp),
                                color = it1
                            )
                        }
                    }
                    val scope = rememberCoroutineScope()
                    repeat(chosenRandomColors.size) {
                        chosenColor.value?.let { it1 ->
                            Modifier
                                .size(130.dp)
                                .background(chosenRandomColors[it].second, shape = CircleShape)
                                .clip(CircleShape)
                                .clickable {
                                    if (winCondition) {
                                        if (timer != 0.0f) {
                                            if (chosenRandomColors[it].first == chosenSolution.value?.first) {
                                                score += 1
                                            } else {
                                                winCondition = false

                                                val user = saveData.retrieveCurrentSession()
                                                user?.name
                                                    ?.let {
                                                        User(
                                                            name = it,
                                                            image = user.image,
                                                            score = score
                                                        )
                                                    }
                                                    ?.let { saveData.insertUser(it) }
                                                scope.launch {
                                                    delay(2000)
                                                    navController.navigate("leaderboard") {
                                                        popUpTo("leaderboard")
                                                    }
                                                }

                                            }
                                        }
                                    }


                                }
                        }?.let { it2 -> Box(modifier = it2, contentAlignment = Alignment.Center) {
                           CircularProgressIndicator(color = Color.White, modifier = Modifier.size(100.dp    ), progress = timer)
                        } }
                        Spacer(modifier = Modifier.height(14.dp))


                    }
                }

            }
        }

    }
}