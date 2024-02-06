package com.example.international_module_c_am

import android.content.Context
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.example.international_module_c_am.ui.theme.Bronze
import com.example.international_module_c_am.ui.theme.CardGray
import com.example.international_module_c_am.ui.theme.DarkBlue
import com.example.international_module_c_am.ui.theme.DarkGray
import com.example.international_module_c_am.ui.theme.Gold
import com.example.international_module_c_am.ui.theme.LightGray
import com.example.international_module_c_am.ui.theme.Silver

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ResultDetailScreen(navController: NavController, toInt: Int) {
    val context = LocalContext.current
    var newsItem = remember { mutableStateListOf<CountryItem>() }

    var item: CountryItem? by remember {mutableStateOf(null)}
    var goldMedal by remember {mutableStateOf(item?.gold)}
    var silverMedal by remember {mutableStateOf(item?.silver)}
    var bronzeMedal by remember {mutableStateOf(item?.bronze)}

    LaunchedEffect(Unit) {
        WorldSkillsAPI().getResults()?.let { newsItem.addAll(it) }
        item = newsItem[toInt]
        goldMedal = item?.gold
        silverMedal = item?.silver
        bronzeMedal = item?.bronze
    }

    val sharedPref = context.getSharedPreferences("settings", Context.MODE_PRIVATE).getBoolean("darkMode", false)
    val text1ColorAsState by animateColorAsState(targetValue = if(sharedPref) Color.White else DarkBlue)
    val text2ColorAsState by animateColorAsState(targetValue = if(sharedPref) Color.LightGray else Color.DarkGray)
    val cardBackgroundAsState by animateColorAsState(targetValue =if (sharedPref) CardGray else Color.White )
    val  background by animateColorAsState(targetValue = if(sharedPref) DarkGray else Color.White)
    var visible = InternetObserver(context).observe().collectAsState(initial = false)
    AnimatedContent(targetState = visible) {
        if(it.value) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .background(background)) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 14.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon( Icons.Default.KeyboardArrowLeft, contentDescription = "Go back", tint =  text1ColorAsState, modifier = Modifier.size(30.dp))


                    }
                    Text(text = "Results", fontWeight = FontWeight.SemiBold, modifier = Modifier.offset(x = -10.dp), color = text2ColorAsState)
                }
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(cardBackgroundAsState)
                    .padding(vertical = 20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
                    if(item?.countryImage != null) {
                        Image(LoaderImage(item?.countryImage!!) , contentDescription = "", modifier = Modifier.requiredSize(130.dp, 80.dp), contentScale = ContentScale.Crop)
                    }else {
                        Image(LoaderImage("") , contentDescription = "", modifier = Modifier.requiredSize(130.dp, 80.dp), contentScale = ContentScale.Crop)
                    }


                    item?.country?.let { Text(text = it, fontWeight = FontWeight.Bold, color = text1ColorAsState, fontSize = 20.sp   ) }


                }
                Column() {
                    LazyColumn(verticalArrangement = Arrangement.Center) {
                        item {
                            Row(modifier= Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Image(painter = painterResource(R.drawable.gold_medal), contentDescription = "Gold medal", modifier = Modifier.size(35.dp))
                                Text(text = "Gold Medals", fontWeight = FontWeight.SemiBold, fontSize = 18.sp, color = Gold )
                            }

                        }
                        item {
                            LazyRow {
                                items(goldMedal?.size ?: 0) {

                                    androidx.compose.material3.Card(modifier = Modifier
                                        .size(200.dp, 260.dp)
                                        .padding(10.dp), colors = CardDefaults.cardColors(cardBackgroundAsState)) {
                                        Column(modifier = Modifier
                                            .padding(top = 6.dp,)
                                            .padding(horizontal = 10.dp)) {

                                            Image(LoaderImage(item?.gold?.get(it)?.avatar!!), contentDescription = "Theordore", modifier = Modifier
                                                .clip(
                                                    RoundedCornerShape(20.dp)
                                                )
                                                .size(170.dp)
                                                .clip(
                                                    RoundedCornerShape(20.dp)
                                                )
                                                .align(Alignment.CenterHorizontally)
                                                .offset(x = 0.dp), contentScale = ContentScale.Crop)
                                            Spacer(modifier = Modifier.height(10.dp ))
                                            goldMedal?.get(it)?.let { it1 -> Text(text = it1.competitorName, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, modifier = Modifier.basicMarquee(), maxLines = 1, color = text2ColorAsState) }
                                            goldMedal?.get(it)?.let { it1 -> Text(text = it1.skillName, modifier = Modifier.basicMarquee(), color = text2ColorAsState) }
                                        }

                                    }
                                }



                            }
                        }
                        item {
                            Row(modifier= Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Image(painter = painterResource(R.drawable.silver_medal), contentDescription = "Silver medal", modifier = Modifier.size(35.dp))
                                Text(text = "Silver Medals", fontWeight = FontWeight.SemiBold, fontSize = 18.sp, color = Silver)
                            }

                        }
                        item {
                            LazyRow {
                                items(goldMedal?.size ?: 0) {

                                    androidx.compose.material3.Card(modifier = Modifier
                                        .size(200.dp, 260.dp)
                                        .padding(10.dp), colors = CardDefaults.cardColors(cardBackgroundAsState)) {
                                        Column(modifier = Modifier
                                            .padding(top = 6.dp,)
                                            .padding(horizontal = 10.dp)) {
                                            Image(LoaderImage(item?.silver?.get(it)?.avatar!!), contentDescription = "Theordore", modifier = Modifier
                                                .clip(
                                                    RoundedCornerShape(20.dp)
                                                )
                                                .size(170.dp)
                                                .clip(
                                                    RoundedCornerShape(20.dp)
                                                )
                                                .align(Alignment.CenterHorizontally)
                                                .offset(x = 0.dp), contentScale = ContentScale.Crop)
                                            Spacer(modifier = Modifier.height(10.dp ))
                                            silverMedal?.get(it)?.let { it1 -> Text(text = it1.competitorName, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, modifier = Modifier.basicMarquee(), maxLines = 1, color = text2ColorAsState) }
                                            silverMedal?.get(it)?.let { it1 -> Text(text =  it1.skillName, modifier = Modifier.basicMarquee(), color = text2ColorAsState) }
                                        }

                                    }
                                }
                            }
                        }



                        item {
                            Row(modifier= Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                                Image(painter = painterResource(R.drawable.bronze_medal), contentDescription = "Bronze medal", modifier = Modifier.size(35.dp))
                                Text(text = "Bronze Medals", fontWeight = FontWeight.SemiBold, fontSize = 18.sp, color = Bronze)
                            }

                        }

                        item {
                            LazyRow {
                                bronzeMedal?.size?.let {
                                    items(it) {

                                        androidx.compose.material3.Card(modifier = Modifier
                                            .size(200.dp, 260.dp)
                                            .padding(10.dp), colors = CardDefaults.cardColors(cardBackgroundAsState)) {
                                            Column(modifier = Modifier
                                                .padding(top = 6.dp,)
                                                .padding(horizontal = 10.dp)) {

                                                Image(LoaderImage(item?.bronze?.get(it)?.avatar!!), contentDescription = "Theordore", modifier = Modifier
                                                    .clip(
                                                        RoundedCornerShape(20.dp)
                                                    )
                                                    .size(170.dp)
                                                    .clip(
                                                        RoundedCornerShape(20.dp)
                                                    )
                                                    .align(Alignment.CenterHorizontally)
                                                    .offset(x = 0.dp), contentScale = ContentScale.Crop)
                                                Spacer(modifier = Modifier.height(10.dp ))
                                                Text(text = bronzeMedal!![it].competitorName, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, modifier = Modifier.basicMarquee(), maxLines = 1, color = text2ColorAsState)
                                                Text(text = bronzeMedal!![it].skillName, modifier = Modifier.basicMarquee(), color = text2ColorAsState)
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
         else {
             NoInternetScreen()
        }
    }

}