package com.example.stroopy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.stroopy.ui.theme.PrimaryGreen

@Composable
fun NicknameScreen(navController: NavController) {
    var nickname by remember {mutableStateOf("")}
    val context = LocalContext.current
    var saveData = remember {SaveData(context)}
    var currentlySelectedProfile by remember {mutableStateOf("")}
    LaunchedEffect(key1 = Unit) {

    }
Column(modifier = Modifier.fillMaxSize(), ) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .background(PrimaryGreen), horizontalArrangement = Arrangement.spacedBy(10.dp , Alignment.CenterHorizontally), verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(R.drawable.img), contentDescription = "Center", contentScale = ContentScale.Crop, modifier = Modifier.size(50.dp    ))
        Text(text = "Stroopy", fontWeight = FontWeight.Bold, fontSize = 30.sp, modifier = Modifier.padding(vertical = 10.dp    ), color = Color.White)

    }
    val context = LocalContext.current
    Spacer(modifier = Modifier.height(10.dp))
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(14.dp), shape = RoundedCornerShape(30.dp), colors = CardDefaults.cardColors(containerColor = Color(
            0xFFF5F5F5
        )
        ) , elevation = CardDefaults.cardElevation(defaultElevation = 10.dp    )) {
            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)) {
                Spacer(modifier = Modifier.height(18.dp))
                Text(text = "Choose your avatar", fontWeight = FontWeight.Black, modifier = Modifier.padding(10.dp   ), fontSize = 24.sp, color = Color.Black)
                LazyVerticalGrid(columns = GridCells.FixedSize(100.dp), modifier = Modifier.offset(x = 20.dp)) {
                    items(9) {

                        Image(painter = painterResource(context.resources.getIdentifier("img_${it+1}", "drawable", "${context.packageName}")), contentDescription ="P", modifier = Modifier
                            .size(90.dp)
                            .border(
                                if (currentlySelectedProfile == "img_${it + 1}") 4.dp else 0.dp,
                                color = if (currentlySelectedProfile == "img_${it + 1}") Color.Black else Color.Transparent,
                                shape = CircleShape
                            )
                            .clip(
                                CircleShape
                            )
                            .clickable {
                                currentlySelectedProfile = "img_${it + 1}"
                            })
                    }

                }
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp), thickness = 2.dp    )
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Nickname",fontWeight = FontWeight.Black, color = Color.Black, fontSize = 24.sp    )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(modifier = Modifier.shadow(10.dp, RoundedCornerShape(60)),value = nickname, onValueChange = {nickname = it}, shape = RoundedCornerShape(60), colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White

                ))
                Spacer(modifier = Modifier.height(20.dp))

                IconButton(onClick = {
                    if(nickname != "" && currentlySelectedProfile!="") {
                       saveData.insertCurrentSession(User(name = nickname, image = currentlySelectedProfile, score = 0  ))
                        navController.navigate("game")
                    }
                   }, modifier = Modifier
                    .size(100.dp)
                    .border(color = PrimaryGreen, shape = CircleShape, width = 3.dp),colors = IconButtonDefaults.filledIconButtonColors(containerColor = Color.White),) {
                    Icon(imageVector = Icons.Default.ArrowForward, contentDescription ="Arrow", tint = PrimaryGreen, modifier = Modifier.size(100.dp ))
                }


            }

        }
    }



}
}