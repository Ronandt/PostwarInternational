package com.example.international

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.international.ui.theme.DarkBlue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, toInt: Int, cat: Int) {
    val context = LocalContext.current
    var item = remember { JsonRetriever(context).retrieveCategory()?.get(cat)?.skills?.get(toInt) }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(14.dp), horizontalAlignment = Alignment.CenterHorizontally) {

        Text(text = item!!.name!!, fontWeight = FontWeight.Bold)
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(item?.newsItems!!.size) {
                var dialog by remember { mutableStateOf(false)}
                var fontSize by remember { mutableStateOf(14.sp)}
                
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp), onClick = {dialog = true}, colors = CardDefaults.cardColors(containerColor = DarkBlue)) {
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = item?.newsItems!!.get(it).newsTitle, fontWeight = FontWeight.Bold, color = Color.White, fontSize = 18.sp   )
                        Text(text = (it + 1).toString(), fontWeight = FontWeight.Bold, color = Color.White, fontSize = 24.sp    )
       
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                        Text(text = item.name   , fontWeight = FontWeight.Bold, color = Color.White, fontSize = 20.sp    )
                        Text(text = item?.newsItems!!.get(it).newsDatetime.toString(), fontWeight = FontWeight.Bold, color = Color.White, fontSize = 20.sp    )
                        
                    }
                    if(dialog) {
                        Dialog(onDismissRequest = { dialog = false }) {
                            Column(modifier = Modifier
                                .fillMaxWidth(0.9f)
                                .fillMaxHeight(0.95f)
                                .background(Color.White)) {
                                Row(modifier = Modifier
                                    .fillMaxWidth()
                                    .background(DarkBlue), horizontalArrangement = Arrangement.SpaceBetween) {
                                    Text(text =item?.newsItems!!.get(it).newsDatetime, color = Color.White, fontWeight = FontWeight.Bold)
                                    IconButton(onClick = {dialog = false}) {
                                        Icon(imageVector = Icons.Default.Close, tint = Color.White, contentDescription = "close")

                                    }
                                }
                                Text(text = item?.newsItems!!.get(it).newsTitle, maxLines = 2, fontWeight = FontWeight.Bold, color = DarkBlue, fontSize = 18.sp)
                                Text(text = item?.newsItems!!.get(it).newsTitle.repeat(10), modifier = Modifier.padding(horizontal = 14.dp), fontSize = fontSize)
                                Row {
                                    IconButton(onClick = { fontSize = fontSize * 0.7f }) {
                                        Icon(painterResource(R.drawable.baseline_minimize_24), contentDescription = "Minus" )

                                    }
                                    Text(text = "Aa", color = Color.White)
                                    IconButton(onClick = { fontSize = fontSize * 1.4f}) {
                                        Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
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