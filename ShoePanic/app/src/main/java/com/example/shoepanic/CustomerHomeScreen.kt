package com.example.shoepanic

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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.shoepanic.ui.theme.DarkBlue
import com.example.shoepanic.ui.theme.DarkRed
import com.example.shoepanic.ui.theme.Header
import com.example.shoepanic.ui.theme.LightGray
import com.example.shoepanic.ui.theme.LightningBlue
import com.example.shoepanic.ui.theme.MainButton


enum class QueueState {
    NotQueued,
    Queueing,
    JoinPhysicalQueue
}
@Composable
fun CustomerHomeScreen(navController: NavController) {
    var queueState by remember {mutableStateOf(QueueState.NotQueued)}
Column(modifier = Modifier
    .fillMaxWidth()
    .padding(horizontal = 20.dp)) {
    Spacer(modifier = Modifier.height(20.dp))
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
        Column {
            Header(text = "Welcome Jane Done!")
            Text(text = "Get ready to queue right now")
        }
        IconButton(onClick = { navController.navigate("login") }) {
            Icon(imageVector = Icons.Default.ExitToApp, contentDescription ="Logout" )
        }

    }

    Card(colors = CardDefaults.cardColors(containerColor = LightGray), modifier = Modifier
        .fillMaxWidth()
        .height(200.dp)
        .padding(vertical = 20.dp)){
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Column(modifier = Modifier.padding(14.dp)) {
                when(queueState) {
                    QueueState.NotQueued -> {
                        Text(
                            text = "You're not in Queue",
                            fontWeight = FontWeight.SemiBold,
                            color = Color.DarkGray,
                            fontSize = 18.sp
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { navController.navigate("scan/blah")}, colors = ButtonDefaults.buttonColors(containerColor = DarkBlue)) {
                            Icon(painter = painterResource(R.drawable.baseline_qr_code_scanner_24), contentDescription = "Scan", tint = Color.White)

                            Text(text = "Queue now", fontWeight = FontWeight.SemiBold, color = Color.White)
                        }
                    }
                    QueueState.Queueing -> {
                        Text(
                            text = "You're not in Queue",
                            fontWeight = FontWeight.SemiBold,
                            color = Color.DarkGray,
                            fontSize = 18.sp
                        )
                        Text(
                            text = "Position: #10",

                            color = Color.DarkGray,
                            fontSize = 16.sp
                        )
                        Text(
                            text = "~50 minutes left",

                            color = Color.DarkGray,
                            fontSize = 16.sp
                        )
                        Box(modifier = Modifier
                            .fillMaxWidth(0.8f)
                            .height(24.dp)) {
                            Spacer(modifier = Modifier
                                .matchParentSize()
                                .background(Color.DarkGray))
                            Spacer(modifier = Modifier
                                .width(100.dp)
                                .background(LightningBlue) )
                        }
                        Button(onClick = { queueState = QueueState.NotQueued }, colors = ButtonDefaults.buttonColors(containerColor = DarkRed)) {
                            Text(text = "Quit Queueing", fontWeight = FontWeight.SemiBold, color = Color.White)

                        }

                    }

                    QueueState.JoinPhysicalQueue ->{
                        Icon(painter = painterResource(R.drawable.baseline_verified_24), contentDescription = "Verified", tint = DarkBlue, modifier = Modifier.offset(y = -18.dp, x = 16.dp))
                        Column {
                            Header(text = "Join the physical queue now!")
                            Row {
                                Icon(painter = painterResource(id = R.drawable.baseline_error_24), contentDescription = "Hint")
                                Text(text = "Please show staff this!")
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(text = "Name: Jane Doe")
                            Text(text = "Position: #0")
                            Row {
                                Button(onClick = { queueState = QueueState.NotQueued }, colors = ButtonDefaults.buttonColors(containerColor = DarkBlue)) {
                                    Text(text = "Physical Scan", fontWeight = FontWeight.SemiBold, color = Color.White)

                                }
                                Button(onClick = { queueState = QueueState.NotQueued }, colors = ButtonDefaults.buttonColors(containerColor = DarkRed)) {
                                    Text(text = "Quit Queueing", fontWeight = FontWeight.SemiBold, color = Color.White)

                                }
                            }
                        }
                        
               

                    }

                }


            }



        }
    }

    Text(buildAnnotatedString {
    append("How did we do? Provide feedback")
        withStyle(style = SpanStyle(color = LightningBlue) ) {
            append(" here now")
        }
    }, modifier = Modifier.clickable {
        navController.navigate("feedback")
    })

}
}