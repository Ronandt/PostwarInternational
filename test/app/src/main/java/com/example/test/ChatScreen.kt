package com.example.test

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.EaseInSine
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Build
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.test.ui.theme.DarkGray
import com.example.test.ui.theme.LightBlue
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavController, string: String?) {
    var prompt by remember {mutableStateOf("")}
    Column(modifier = Modifier
        .background(Color.Black)
        .fillMaxSize()) {
        Scaffold(modifier  = Modifier
            .fillMaxSize()
            .background(Color.Black),topBar =  {
            CenterAlignedTopAppBar(colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Black),title = { Text("Assistant Greeting", color = Color.White) }, navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back", tint = Color.White)
                }
            }, actions = {
                Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "profile image", modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .size(34.dp))
            })
        }) {

            Column(modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(it)
                .padding(horizontal = 10.dp)) {
                Spacer(modifier = Modifier.height(14.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
                    Box(modifier = Modifier

                        .background(
                            shape = RoundedCornerShape(10.dp, 0.dp, 10.dp, 10.dp),
                            color = DarkGray
                        )) {
                        Text(text = string!!, color = Color.White, modifier = Modifier.padding(10.dp))


                    }
                }
                Spacer(modifier = Modifier.height(14.dp))

            Shimmer {
               Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {

                   Icon(imageVector = Icons.Default.Star, contentDescription = "Yes", tint = Color.White)
                   Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "", tint = Color.White)
               }
               Spacer(modifier = Modifier.height(10.dp))
               Text(text = "Hello! How can I help you today", modifier = Modifier.padding(vertical = 18.dp), color = Color.White, fontSize = 18.sp )
               Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(14.dp  )) {
                   IconButton(onClick = { /*TODO*/ }) {
                       Icon(imageVector = Icons.Outlined.ThumbUp, contentDescription = "Thumps up", tint = Color.White)

                   }
                   IconButton(onClick = { /*TODO*/ }) {
                       Icon(imageVector = Icons.Outlined.AccountBox, contentDescription = "Thumps up", tint = Color.White)

                   }
                   IconButton(onClick = { /*TODO*/ }) {
                       Icon(imageVector = Icons.Outlined.Share, contentDescription = "Thumps up", tint = Color.White)

                   }
                   IconButton(onClick = { /*TODO*/ }) {
                       Icon(imageVector = Icons.Outlined.Build, contentDescription = "Thumps up", tint = Color.White)

                   }
                   IconButton(onClick = { /*TODO*/ }) {
                       Icon(imageVector = Icons.Outlined.Call, contentDescription = "Thumps up", tint = Color.White)

                   }
               }
           }
                Spacer(modifier = Modifier.weight(1f))
                Divider(color = DarkGray)

                OutlinedTextField(value =prompt     , onValueChange = {prompt = it}, shape = RoundedCornerShape(20.dp), modifier = Modifier.fillMaxWidth(), trailingIcon = {
                    Row(modifier = Modifier
                        .padding(10.dp)
                        .width(100.dp)
                        .background(color = LightBlue, shape = RoundedCornerShape(100.dp)), horizontalArrangement = Arrangement.SpaceEvenly) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Phone, contentDescription = "")
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.Create, contentDescription = "")
                        }
                    }
                })
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Previews() {
    ChatScreen(navController = rememberNavController(), string ="" )
}
@Composable
fun Shimmer(successCallback: @Composable () -> Unit) {
    var loading by remember {mutableStateOf(true)}
 //   val animateFloat by animateFloatAsState(targetValue = 1000f)
    val infiniteTrans = rememberInfiniteTransition()
    val float by infiniteTrans.animateFloat(initialValue = 0f, targetValue = 12200f, animationSpec = infiniteRepeatable(
        repeatMode = RepeatMode.Restart,
        animation = tween(1000, easing = LinearEasing)
    ))
    LaunchedEffect(Unit) {
        delay(4000)
        loading = false
        //artifical timer
    }
    if(loading) {
        Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            repeat(3) {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(24.dp)
                    .background(
                        Brush.horizontalGradient(
                            listOf(Color.DarkGray, Color.LightGray, Color.DarkGray, Color.LightGray, Color.DarkGray),
                            startX = -1f,
                            endX = float
                        )
                    ))
            }
        }
    } else {
        successCallback()
    }
   
}

//good answer
