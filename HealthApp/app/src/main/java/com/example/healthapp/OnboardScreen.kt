package com.example.healthapp

import android.widget.NumberPicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.healthapp.ui.theme.Dark
import com.example.healthapp.ui.theme.LightGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OnboardScreen(navController: NavController) {

    var username by remember { mutableStateOf("")}
    var cm by remember {mutableStateOf("0")}
    var kg by remember {mutableStateOf("0")}
    var birthday by remember {mutableStateOf("")}
    var steps by remember { mutableStateOf("0")}
    var cmPoint by remember {mutableStateOf("0")}
    var ml by remember { mutableStateOf("0")}
    var selection by remember {mutableStateOf("male")}
    var tallDialog by remember {mutableStateOf(false)}
    var weightDialog by remember {mutableStateOf(false)}
    var stepsDialog by remember {mutableStateOf(false)}
    var waterDialog by remember {mutableStateOf(false)}



    Scaffold(topBar = {
        TopAppBar(navigationIcon = {
                                   IconButton(onClick = { navController.navigateUp()}) {
                                       Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                                   }
        },title = { Text(text = "Profile & Target", color = Color.White, fontWeight = FontWeight.SemiBold)}, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Dark))
    }) {
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(12.dp)
            .padding(it)) {
            Text(text = "Hi username99", fontSize = 26.sp, modifier = Modifier.padding(vertical = 10.dp    ))
            Text(text = "Profile", fontWeight = FontWeight.Bold)
            Row(modifier = Modifier
                .fillMaxWidth(0.7f)
                .padding(vertical = 10.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                Card(colors = CardDefaults.cardColors(containerColor = LightGray), onClick = {selection ="male" }, modifier = Modifier.border(width = if(selection == "male") 2.dp else 0.dp, color = Color.Black  )) {
                    Column(modifier = Modifier
                        .width(120.dp)
                        .height(160.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(R.drawable.man_fill0_wght400_grad0_opsz24), contentDescription = "man", contentScale = ContentScale.Crop, modifier = Modifier.size(100.dp   ) )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Male")
                    }
                }
                Card(colors = CardDefaults.cardColors(containerColor = LightGray), onClick = {selection = "female"}, modifier = Modifier.border(width = if(selection == "female") 2.dp else 0.dp, color = Color.Black  )) {
                    Column(modifier = Modifier
                        .width(120.dp)
                        .height(160.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                        Image(painter = painterResource(R.drawable.woman_fill0_wght400_grad0_opsz24), contentDescription = "female", contentScale = ContentScale.Crop, modifier = Modifier.size(100.dp   ) )
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = "Female")
                    }
                }
            }
            Column(verticalArrangement = Arrangement.spacedBy(8.dp  )) {
                ApplicationTextField(value = username , onValueChange = {username = it}, placeholder = "Competitor99", clickable = true, leadingIcon = {
                    Icon(imageVector = Icons.Default.Person, contentDescription ="Person", tint = Color.DarkGray, modifier = Modifier.size(30.dp    ))
                })
                ApplicationTextField(value = cm + "." + cmPoint, onValueChange = {cm = it}, placeholder = "", clickable = true, trailingIcon = { Text(
                    text = "Cm"
                )}, leadingIcon = {
                    Icon(painterResource(R.drawable.height_svgrepo_com), contentDescription = "Height", tint = Color.DarkGray, modifier = Modifier.size(30.dp    ),)
                }, onClick = {tallDialog = true})
                ApplicationTextField(value = kg, onValueChange = {kg = it}, placeholder = "", clickable =true, trailingIcon = { Text(
                    text = "Kg"
                )}, leadingIcon = {
                    Icon(painterResource(R.drawable.weight_svgrepo_com), contentDescription = "Height", tint = Color.DarkGray, modifier = Modifier.size(30.dp    ))
                }, onClick = {weightDialog = true})

                ApplicationTextField(value = birthday, onValueChange = {birthday = it}, placeholder = "", clickable = false, leadingIcon = {
                    Icon(painterResource(R.drawable.cake_svgrepo_com), contentDescription = "Height", tint = Color.DarkGray, modifier = Modifier.size(30.dp    ))
                })
            }

            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Target", fontWeight = FontWeight.Bold, fontSize = 26.sp)
            Column(verticalArrangement = Arrangement.spacedBy(8.dp  )) {
                ApplicationTextField(value = steps, onValueChange = {steps = it}, placeholder = "", clickable = true,  leadingIcon = {
                    Icon(painterResource(id = R.drawable.footprint_fill0_wght400_grad0_opsz24), contentDescription ="footprint",  modifier = Modifier.size(30.dp    ) )
                }, trailingIcon = { Text(text = "Steps")}, onClick = {stepsDialog = true})
                ApplicationTextField(value = ml, onValueChange = {ml = it}, placeholder = "", clickable = true,  leadingIcon = {
                    Icon(painterResource(id = R.drawable.water_drop_fill0_wght400_grad0_opsz24), contentDescription ="footprint", modifier = Modifier.size(30.dp    )  )
                }, trailingIcon = { Text(text = "ml")}, onClick = {waterDialog = true})
            }
            Spacer(modifier = Modifier.height(10.dp))
            ApplicationTextButton(text = "Complete") {
                navController.navigate("home")
            }


        }
    }







    if(waterDialog) {
        Dialog(onDismissRequest = { waterDialog = false}) {
            ApplicationDialog(title = "Water", content = {
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(20.dp))
                    AndroidView(factory = {
                        NumberPicker(it).apply {
                            this.value = ml.toInt()
                            this.maxValue = 10000
                            this.minValue = 0
                        }

                    }, update = {
                        it.setOnValueChangedListener { picker, oldVal, newVal ->
                           ml = newVal.toString()

                        }
                    })
                    Text(text = "ml", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                }
            }, onComplete = { waterDialog = false }) {
                waterDialog = false



            }
        }
    }







    if(stepsDialog) {
        Dialog(onDismissRequest = { stepsDialog= false}) {
            ApplicationDialog(title = "Steps", content = {
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(20.dp))
                    AndroidView(factory = {
                        NumberPicker(it).apply {
                            this.value = steps.toInt()
                            this.maxValue = 20000
                            this.minValue = 1000
                        }

                    }, update = {
                        it.setOnValueChangedListener { picker, oldVal, newVal ->
                            steps= newVal.toString()

                        }
                    })
                    Text(text = "Steps", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                }
            }, onComplete = { stepsDialog = false }) {
                stepsDialog = false



            }
        }
    }



    if(weightDialog) {
        Dialog(onDismissRequest = { weightDialog= false}) {
            ApplicationDialog(title = "Kg", content = {
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(20.dp))
                    AndroidView(factory = {
                        NumberPicker(it).apply {
                            this.value = kg.toInt()
                            this.maxValue = 10000
                            this.minValue = 1000
                        }

                    }, update = {

                        it.setOnValueChangedListener { picker, oldVal, newVal ->
                            kg= newVal.toString()

                        }
                    })
                    Text(text = "Weight ", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                }
            }, onComplete = { weightDialog = false }) {

                weightDialog = false


            }
        }
    }




    if(tallDialog) {
        Dialog(onDismissRequest = { tallDialog= false}) {
            ApplicationDialog(title = "Height", content = {

                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Spacer(modifier = Modifier.width(20.dp))
                    AndroidView(factory = {
                         NumberPicker(it).apply {
                            this.value = cm.toInt()
                            this.maxValue = 200
                            this.minValue = 0

                        }


                    }, update = {
                        it.setOnValueChangedListener { picker, oldVal, newVal ->
                             cm = newVal.toString()

                        }
                    })



                    Text(text = ".", fontWeight = FontWeight.Bold, fontSize = 24.sp)

                    AndroidView(factory = {
                        NumberPicker(it).apply {
                            this.value = cmPoint.toInt()
                            this.maxValue = 9
                            this.minValue = 0
                        }


                    }, update = {
                        cmPoint = it.value.toString()
                        it.setOnValueChangedListener { picker, oldVal, newVal ->
                            cmPoint= newVal.toString()

                        }
                    })

                    Text(text = "Cm", fontWeight = FontWeight.Bold, fontSize = 24.sp)
                }
            }, onComplete = { tallDialog = false }) {
                tallDialog =false



            }
        }
    }











}