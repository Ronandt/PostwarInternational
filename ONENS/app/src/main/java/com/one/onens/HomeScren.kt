package com.one.onens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(Modifier.verticalScroll(rememberScrollState())  ) {
        Box {
            Image(painter = painterResource(id = R.drawable.salute), contentDescription = "Salute", modifier = Modifier
                .fillMaxWidth()
                .height(300.dp), contentScale = ContentScale.Crop)
            Spacer(modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .background(brush = Brush.verticalGradient(listOf(Color.Black, Color(0x00000000)))))
            Spacer(modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(brush = Brush.verticalGradient(listOf(Color(0x00000000), Color.Black))))
            Row(horizontalArrangement = Arrangement.spacedBy(1.dp), modifier = Modifier
                .padding(10.dp)
                .padding(top = 16.dp)
                .align(Alignment.TopEnd)) {
                IconButton(onClick = { navController.navigate("search") }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color.White )

                }
                IconButton(onClick = { navController.navigate("inbox")}) {
                    Icon(Icons.Default.Notifications, contentDescription = "Bell", tint = Color.White )
                }
            }
            Column(modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterStart)) {
                Text(text = "HELLO", fontWeight = FontWeight.Bold, color =  Color.Red, fontSize = 20.sp )
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "DECLAN ISAAC KUOK LI\nHENG", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 20.sp   )

            }

        }
        Column(modifier = Modifier
            .background(Color(0xFFDD2939))
            .padding(10.dp)
           
            ) {
            Column(
                Modifier
                    .fillMaxWidth()

                    ) {

                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(6.dp   ), modifier = Modifier.padding(vertical = 10.dp    )) {

                    Text(text = "Action Required ", fontWeight = FontWeight.Bold, color = Color.White, fontSize = 20.sp )
                    Box(
                        Modifier
                            .size(24.dp)
                            .background(Color.White, shape = RoundedCornerShape(4.dp))) {
                        Text(text = "1", fontWeight = FontWeight.Bold, color = Color(0xFFDD2939), modifier = Modifier.align(Alignment.Center))
                        
                    }
                    
                }
            }
            Card(colors =  CardDefaults.cardColors(Color.White)) {
                Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFDF8E1))
                    .padding(10.dp)) {
                    Icon(Icons.Default.Warning, contentDescription =  "ending", tint = Color(0xFFD86F20))
                    Text(text = "PENDING ENLISTMENT", fontWeight = FontWeight.Bold, color = Color(0xFFD86F20))
                }
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Scheduled for enlistent",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "You will be enlisted for national service in the\n SAF. pleASe report to BMTC School V on\n07 Oct 2024. you may refer to the Enlistment\nNotice for more details.")
                }
                Spacer(modifier = Modifier.height(10.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(2.dp,   ), verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFFAFAFA))) {
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = "Learn more", fontWeight = FontWeight.SemiBold, color = Color.DarkGray)
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_highlight_alt_24), contentDescription =  "Mrk", tint = Color.DarkGray)
                    }
                }
            }




        }
        Column(modifier = Modifier.padding(16.dp)) {
            Card(colors =  CardDefaults.cardColors(Color.White)) {
                Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE2FAEC))
                    .padding(10.dp)) {
                    Icon(Icons.Default.CheckCircle, contentDescription =  "ending", tint = Color(0xFF116B24))
                    Text(text = "TASK COMPLETED ON 24 FEB 2023", fontWeight = FontWeight.Bold, color = Color(0xFF116B24))
                }
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Complete Medical Screening\nQuestionnaire",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = "Your Medica Screening Questionnaire has been Endorsede by your parent/guardian")
                }
                Spacer(modifier = Modifier.height(10.dp))

            }
        }




    }




}