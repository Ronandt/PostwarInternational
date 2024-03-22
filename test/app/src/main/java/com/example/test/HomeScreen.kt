package com.example.test

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.test.ui.theme.DarkGray
import com.example.test.ui.theme.LightBlue
import com.example.test.ui.theme.RealBlue
import com.example.test.ui.theme.RealPurple
import com.example.test.ui.theme.RealSunset

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    var typedYet by remember {mutableStateOf(false)}
    var textPrompt by remember {mutableStateOf("Type, talk or share a photo")}
    var bottomSheetScaffoldState = rememberBottomSheetScaffoldState()
    BottomSheetScaffold(sheetContainerColor = Color.Black,sheetPeekHeight = 260.dp,sheetContent = {
        Column(modifier = Modifier
            .height(220.dp)
            .padding(14.dp), verticalArrangement = Arrangement.SpaceBetween) {
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
               BasicTextField(value =  textPrompt, {
                   if(typedYet) {
                       if(it == "") {
                           typedYet = false
                           textPrompt = "Type, talk or share a photo"
                           return@BasicTextField
                       }
                       textPrompt = it
                   } else {
                       typedYet = true
                       textPrompt = ""
                   }


                   }, textStyle = TextStyle(Color.LightGray, fontSize = 24.sp    ))

                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Add, contentDescription = "Expand")
                }
            }
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Spacer(Modifier.size(30.dp))
                Row(modifier = Modifier
                    .width(100.dp)
                    .background(color = LightBlue, shape = RoundedCornerShape(100.dp)), horizontalArrangement = Arrangement.SpaceEvenly) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Phone, contentDescription = "")
                    }
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Default.Create, contentDescription = "")
                    }
                }
                IconButton(enabled = typedYet,onClick = {       if(typedYet) {
                    navController.navigate("chat/${textPrompt}")
                }}) {
                    Icon(imageVector = Icons.Default.Send, contentDescription = "Send", tint = Color.LightGray)
                }

            }



        }

    }, scaffoldState = bottomSheetScaffoldState) {
        Column(modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
            .padding(10.dp)) {
            Row(modifier = Modifier
                .padding(bottom = 34.dp)) {
                Spacer(modifier = Modifier.weight(1f))
                Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "Profile picture image", contentScale = ContentScale.Crop, modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .size(30.dp))


            }
            Text(modifier = Modifier.padding(bottom = 10.dp), text = "Good Afternoon", fontWeight = FontWeight.Medium, fontSize = 34.sp, style = TextStyle(brush =  Brush.horizontalGradient(listOf(RealBlue, RealPurple, RealSunset))))
            LazyRow(horizontalArrangement = Arrangement.spacedBy(4.dp   ), modifier = Modifier.padding(bottom = 10.dp   )) {
                items(5) {
                    Card(onClick = { /*TODO*/ }, modifier = Modifier.size(170.dp), colors = CardDefaults.cardColors(containerColor = DarkGray)) {
                        Column(modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp), verticalArrangement = Arrangement.SpaceBetween) {
                            Text(text = "Settle ad ebate: How should you store bread", color = Color.White)
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                                IconButton(
                                    onClick = {

                                  },
                                    colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Black), enabled = typedYet
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ShoppingCart,
                                        contentDescription = "cart",
                                        tint = Color.White
                                    )

                                }
                            }
                        }

                    }
                }
            }

            Row(modifier = Modifier
                .fillMaxWidth()
               , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Recent", color = Color.White, fontWeight = FontWeight.Medium)
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = "view recents", tint = Color.White)

                }

            }
            Spacer(modifier = Modifier.height(14.dp))
            LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){
                items(3) {
                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                        Box(modifier = Modifier.size(26.dp).background(DarkGray, shape = RoundedCornerShape(10.dp)), contentAlignment = Alignment.Center) {
                            Icon(imageVector = Icons.Default.Info, contentDescription = "History", tint = Color.White)
                        }
                        Text(text = "Assistant greeting", color = Color.White)

                    }
                }
            }
            Spacer(modifier = Modifier.height(100.dp))






        }
    }

}