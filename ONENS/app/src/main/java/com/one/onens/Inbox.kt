package com.one.onens

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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun InboxScreen(navController: NavController) {
    Column() {
        Column(modifier = Modifier.fillMaxWidth()
            .background(Color(0xFF161C28)).padding(10.dp    )) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Arorw", tint = Color.White )
            }

            Text(text = "Inbox", color = Color.White, fontWeight = FontWeight.SemiBold, fontSize = 22.sp  , modifier = Modifier.padding(horizontal = 10.dp  )  )

        }
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.padding(10.dp  )) {
            Image(painter = painterResource(id = R.drawable.inbox), contentDescription =  "Inbox", modifier = Modifier.size(200.dp  ))
            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Awesome you are clear", fontWeight = FontWeight.Bold, fontSize = 24.sp)
        }
     

    }

}