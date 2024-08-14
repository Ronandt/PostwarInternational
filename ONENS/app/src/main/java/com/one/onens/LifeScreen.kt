package com.one.onens

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
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
fun LifeScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)
        .padding(horizontal = 10.dp)) {
        Spacer(modifier = Modifier.height(50.dp))

        Image(painter = painterResource(id = R.drawable.life), contentDescription = "life", modifier = Modifier
            .align(
                Alignment.CenterHorizontally
            )
            .size(200.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "To accessy our credits, visit\nGovwallet in the LifeSG app.",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Typews of credits avaliable:", fontWeight = FontWeight.Bold)
        Column(verticalArrangement = Arrangement.spacedBy(14.dp )) {
            Text(text = "- NS Excellence award")
            Text(text = "- Celebratory Gifts")
            Text(text = "- NS HOME Awards")
            Text(text = "-NS55 Recognition akage ")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFDD2939))) {
            Text(text = "OPEN LIFESG APP", color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(8.dp ))

        }

        Text(text = "What the LifeSG website for more information", color =  Color(0xFFDD2939) )



    }
}