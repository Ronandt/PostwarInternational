package com.example.shoepanic

import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shoepanic.ui.theme.DarkBlue
import com.example.shoepanic.ui.theme.Header
import com.example.shoepanic.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaffHomeScreen(navController: NavController) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {
        Header(text = "Welcome Jane Staff!")
        Text(text = "Monitor your queue with ease here")
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            Card(modifier = Modifier.size(170.dp, 100.dp), colors = CardDefaults.cardColors(
                LightGray)) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Text(text = "Total Queuers", fontWeight = FontWeight.SemiBold)
                    Text(text = "50", color = DarkBlue, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }



            }
            Card(modifier = Modifier.size(170.dp, 100.dp), colors = CardDefaults.cardColors(

                LightGray)) {
                Column(modifier = Modifier.padding(15.dp)) {
                    Text(text = "Avg Waiting Time", fontWeight = FontWeight.SemiBold)
                    Text(text = "7 minutes", color = DarkBlue, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                }


            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(220.dp), colors = CardDefaults.cardColors(containerColor = LightGray)) {
            Column(modifier = Modifier.padding(10.dp)) {
                Text(text = "Queue length", fontWeight = FontWeight.SemiBold, fontSize = 17.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(18.dp), verticalAlignment = Alignment.Bottom){
                    (0..8).forEach {
                        Spacer(modifier = Modifier
                            .height(150.dp)
                            .width(20.dp)
                            .background(Color.Black))
                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(130.dp), colors = CardDefaults.cardColors(
            LightGray), onClick = {
                navController.navigate("queue")
        }) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)) {
                Text(text = "Main Queue", fontWeight = FontWeight.SemiBold, fontSize = 18.sp    )
                Text(text = "People in queue: 10")
                Text(text = "Estimated time: 50 minutes")
            }

        }
        Spacer(modifier = Modifier.height(20.dp))
        Card(modifier = Modifier
            .fillMaxWidth()
            .height(130.dp), colors = CardDefaults.cardColors(
            LightGray)) {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)) {
                Text(text = "Special Queue", fontWeight = FontWeight.SemiBold, fontSize = 18.sp    )
                Button(onClick = { navController.navigate("specialQueue") }, colors = ButtonDefaults.buttonColors(containerColor = DarkBlue)) {
                    Text(text = "Create Queue", fontWeight = FontWeight.SemiBold, color = Color.White)

                }
            }

        }
    }

}