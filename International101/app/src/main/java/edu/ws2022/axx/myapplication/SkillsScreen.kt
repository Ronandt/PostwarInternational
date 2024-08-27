package edu.ws2022.axx.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter.State.Empty.painter

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SkillsScreen(navController: NavController) {
    val context = LocalContext.current
    var listOfSkills = remember { mutableStateListOf<Category>()}
    val api = remember { API() }
    LaunchedEffect(Unit) {
      listOfSkills.addAll(api.skillsAndCategory())

    }
    Column(Modifier.fillMaxSize()) {

        LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp )) {
            items(listOfSkills.size) {
                val category = listOfSkills[it].category
                val i = listOfSkills[it]
                Row(modifier = Modifier
                    .background(Color.Black)
                    .width(250.dp)) {
                    Text(
                        text =  category,
                        color = Color.White,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(10.dp)
                    )
                }
                var dialog by remember {mutableStateOf(-1)}
                if(dialog != -1) {
                    //Horizontal
                    Dialog(onDismissRequest = { dialog = -1}) {
                        Column(modifier = Modifier
                            .width(800.dp)
                            .height(800.dp)
                            .background(Color.White)
                            .padding(10.dp)) {
                            Text(
                                text = if(dialog == -1) "" else  i.trades[dialog].skillName,
                                modifier = Modifier,
                                fontWeight = FontWeight.Bold,
                                fontSize = 28.sp
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            Row(horizontalArrangement = Arrangement.spacedBy(20.dp  )) {
                                Text(text = if(dialog == -1) "" else i.trades[dialog].text, modifier = Modifier.width(300.dp))
                                Column(){
                                    AsyncImage(if(dialog == -1) "" else i.trades[dialog].imageLink, contentDescription ="Backgrond", modifier = Modifier.width(300.dp  ).height(200.dp ).clip(
                                        RoundedCornerShape(10.dp    )), contentScale = ContentScale.Crop)
                                    Spacer(modifier = Modifier.weight(1f))

                                    Button(enabled = dialog < i.trades.size -1,onClick = {  dialog = dialog + 1}, modifier = Modifier.align(Alignment.End)) {
                                        Text(text = "Next")
                                    }
                                }
                            }

                        }
                    }
                }
                //state mangemnet for the thing

                FlowRow(maxItemsInEachRow = 3, horizontalArrangement = Arrangement.spacedBy(10.dp   )) {
                    repeat(i.trades.size) {
                        Row(horizontalArrangement = Arrangement.SpaceBetween,modifier = Modifier
                            .width(280.dp)
                            .clickable { dialog = it }
                            .padding(20.dp)
                           ) {
                            Text(text = "${i.trades[it].skillName}")
                            Icon(Icons.Default.Add, contentDescription = "Add")
                        }
                    }

                }
            }
        }
    }
}