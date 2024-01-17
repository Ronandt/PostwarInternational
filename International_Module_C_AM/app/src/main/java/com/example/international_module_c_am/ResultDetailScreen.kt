package com.example.international_module_c_am

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.international_module_c_am.ui.theme.DarkBlue

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


Column(modifier = Modifier.fillMaxWidth()) {
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 14.dp), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = { navController.navigateUp() }) {
            Icon( Icons.Default.KeyboardArrowLeft, contentDescription = "Go back", tint = DarkBlue, modifier = Modifier.size(30.dp))


        }
        Text(text = "Results", fontWeight = FontWeight.SemiBold)
    }
    Column(modifier = Modifier
        .fillMaxWidth()
        .height(180.dp)
        .background(Color.LightGray)
        .padding(vertical = 20.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceAround) {
        AsyncImage(model = item?.countryImage , contentDescription = "", modifier = Modifier.size(130.dp, 80.dp), contentScale = ContentScale.Crop)

        item?.country?.let { Text(text = it, fontWeight = FontWeight.Bold, color = DarkBlue, fontSize = 20.sp   ) }


    }
    Column() {
        LazyColumn(verticalArrangement = Arrangement.Center) {
            item {
                Row(modifier= Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Image(painter = painterResource(R.drawable.gold_medal), contentDescription = "Gold medal", modifier = Modifier.size(35.dp))
                    Text(text = "Gold Medals", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                }

            }
            item {
                LazyRow {
                    items(goldMedal?.size ?: 0) {

                        androidx.compose.material3.Card(modifier = Modifier
                            .size(200.dp, 260.dp)
                            .padding(10.dp)) {
                            Column(modifier = Modifier.padding(top = 6.dp,).padding(horizontal = 10.dp)) {

                                AsyncImage(item?.gold?.get(it)?.avatar, contentDescription = "Theordore", modifier = Modifier.clip(
                                    RoundedCornerShape(20.dp))
                                    .size(170.dp)
                                    .clip(
                                        RoundedCornerShape(20.dp)
                                    ).align(Alignment.CenterHorizontally).offset(x = 0.dp), contentScale = ContentScale.Crop)
                                Spacer(modifier = Modifier.height(10.dp ))
                                goldMedal?.get(it)?.let { it1 -> Text(text = it1.competitorName, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, modifier = Modifier.basicMarquee(), maxLines = 1) }
                                goldMedal?.get(it)?.let { it1 -> Text(text = it1.skillName, color = Color.DarkGray, modifier = Modifier.basicMarquee()) }
                            }

                        }
                    }



                }
            }
            item {
                Row(modifier= Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Image(painter = painterResource(R.drawable.silver_medal), contentDescription = "Gold medal", modifier = Modifier.size(35.dp))
                    Text(text = "Silver Medals", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                }

            }
            item {
                LazyRow {
                    items(goldMedal?.size ?: 0) {

                        androidx.compose.material3.Card(modifier = Modifier
                            .size(200.dp, 260.dp)
                            .padding(10.dp)) {
                            Column(modifier = Modifier.padding(top = 6.dp,).padding(horizontal = 10.dp)) {
                                AsyncImage(item?.silver?.get(it)?.avatar, contentDescription = "Theordore", modifier = Modifier.clip(
                                    RoundedCornerShape(20.dp))
                                    .size(170.dp)
                                    .clip(
                                        RoundedCornerShape(20.dp)
                                    ).align(Alignment.CenterHorizontally).offset(x = 0.dp), contentScale = ContentScale.Crop)
                                Spacer(modifier = Modifier.height(10.dp ))
                                silverMedal?.get(it)?.let { it1 -> Text(text = it1.competitorName, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, modifier = Modifier.basicMarquee(), maxLines = 1) }
                                silverMedal?.get(it)?.let { it1 -> Text(text =  it1.skillName, color = Color.DarkGray, modifier = Modifier.basicMarquee()) }
                            }

                        }
                    }
                }
            }



            item {
                Row(modifier= Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Image(painter = painterResource(R.drawable.bronze_medal), contentDescription = "Gold medal", modifier = Modifier.size(35.dp))
                    Text(text = "Bronze Medals", fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
                }

            }

            item {
                LazyRow {
                    bronzeMedal?.size?.let {
                        items(it) {

                            androidx.compose.material3.Card(modifier = Modifier
                                .size(200.dp, 260.dp)
                                .padding(10.dp)) {
                                Column(modifier = Modifier.padding(top = 6.dp,).padding(horizontal = 10.dp)) {
                                    AsyncImage(item?.bronze?.get(it)?.avatar, contentDescription = "Theordore", modifier = Modifier.clip(
                                        RoundedCornerShape(20.dp))
                                        .size(170.dp)
                                        .clip(
                                            RoundedCornerShape(20.dp)
                                        ).align(Alignment.CenterHorizontally).offset(x = 0.dp), contentScale = ContentScale.Crop)
                                    Spacer(modifier = Modifier.height(10.dp ))
                                    Text(text = bronzeMedal!![it].competitorName, fontWeight = FontWeight.SemiBold, fontSize = 18.sp, modifier = Modifier.basicMarquee(), maxLines = 1)
                                    Text(text = bronzeMedal!![it].skillName, color = Color.DarkGray, modifier = Modifier.basicMarquee())
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