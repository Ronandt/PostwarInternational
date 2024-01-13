package com.example.international_module_c_am

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.international_module_c_am.ui.theme.DarkBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultsScreen(navController: NavController) {
    val context = LocalContext.current
    var results = remember { mutableStateListOf<CountryItem>() }
    LaunchedEffect(Unit) {
        WorldSkillsAPI().getResults()?.let { results.addAll(it) }


    }


    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Mock data", color = Color.DarkGray)
            Image(painter = painterResource(R.drawable.medals), contentDescription = "Medals", modifier = Modifier.size( 100.dp, 20.dp,), contentScale = ContentScale.Crop)

            }

        Spacer(modifier = Modifier.height(10.dp))



        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            results?.size?.let {
                items(it) {b->

                    Card(modifier  = Modifier
                        .fillMaxWidth()
                        .height(80.dp), onClick = {
                        navController.navigate("resultDetail/$b")

                    }) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()){
                            Row {
                                Image(painter = painterResource(R.drawable.korea    ), contentDescription = "Logo", modifier = Modifier


                                    .width(80.dp).padding(10.dp).align(Alignment.CenterVertically), contentScale = ContentScale.Crop, )
                                Column(modifier = Modifier
                                    .padding(vertical = 14.dp)
                                    .fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween, ) {
                                    Text(text = "${results[b].country}", fontWeight = FontWeight.Bold,  fontSize = 18.sp)
                                    Text("#${b + 1}", maxLines = 1, overflow = TextOverflow.Ellipsis, color = DarkBlue, fontSize = 18.sp, fontWeight = FontWeight.Bold)
                                }
                            }


                            Row(horizontalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterHorizontally), verticalAlignment = Alignment.CenterVertically, modifier = Modifier.offset(x = -13.dp).align(Alignment.CenterVertically)) {
                                repeat(4) {
                                    if(it == 0) {
                                        Text(text = results[b].gold.size.toString(), color = Color.Blue  )
                                    }
                                    if(it == 1) {
                                        Text(text = results[b].silver.size.toString(), color = Color.DarkGray)
                                    }
                                    if(it == 2) {
                                        Text(text = results[b].bronze  .size.toString(), color = Color(
                                            0xFF5E2D0D
                                        )
                                        )
                                    }

                                    if(it == 3) {
                                        Text(text = results[b].excellence.toString())
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }

    }

}