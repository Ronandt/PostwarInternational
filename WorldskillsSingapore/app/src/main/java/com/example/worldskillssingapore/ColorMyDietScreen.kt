package com.example.worldskillssingapore

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.worldskillssingapore.ui.theme.RedColor
import io.realm.kotlin.ext.query
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorMyDietScreen(navController: NavController) {
    val dateList = listOf("1M", "3M", "6M", "1Y")
    var choiceSelection by remember {mutableStateOf("1M"   )}
    var foodFilter = remember {mutableStateListOf<FoodDbData>()}
    var monthTime = remember {System.currentTimeMillis() - 1000L*60L*60L*24L*30L}
    var threeMontTime = remember {System.currentTimeMillis() - 1000L*60L*60L*24L*30L * 3}
    var sixMonthTime = remember {
        System.currentTimeMillis() -
                1000L*60L*60L*24L*30L   * 6}
    var yearTime = remember {
        System.currentTimeMillis() -
                1000L*60L*60L*24L*30L   * 12}

    var database = remember {FoodDB.getDb()}

    var foodFlow = database.query<FoodDbData>().find().asFlow().map { it.list }.collectAsState(
        initial = listOf()
    )

    LaunchedEffect(Unit) {
        while(true) {
            var selection = when(choiceSelection) {
                "1M" -> {monthTime}
                "3M" -> {threeMontTime}
                "6M" -> {sixMonthTime}
                "1Y" -> {yearTime}
                else -> {monthTime}
            }
            if(foodFilter.size != 0) {
                foodFilter.clear()

            }

            println(foodFlow.value.toList())


            foodFilter.addAll(foodFlow.value.filter {
                it.unixDate > selection
            })
            println("DEBUGG")
            println(foodFilter.toList())
            delay(1000L)
        }


    }






    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "F&B's near me", color = Color.White, fontWeight = FontWeight.SemiBold) }, navigationIcon = {


        }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = RedColor))

    }) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(it)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                SingleChoiceSegmentedButtonRow {
                    dateList.forEachIndexed { index, s ->

                        SegmentedButton(selected = choiceSelection == s, onClick = { choiceSelection = s }, shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = dateList.size
                        ) ) {
                            Text(text = s, color = RedColor)
                        }

                    }
                }
            }

            
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp), horizontalArrangement = Arrangement.SpaceEvenly,) {
                repeat(3) {
                    var color = when(it) {
                        0 -> { Color(0xFFB81D13)}
                        1 -> {Color(0xFFEFB700)}
                         2 -> {Color(0xFF008450)}
                        else -> {Color.Black}
                    }

                    Row(horizontalArrangement = Arrangement.spacedBy(4.dp   )){
                        Box(modifier = Modifier.background(color, shape = CircleShape) )
                    }
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Text(text = "No. of Food Items", fontWeight = FontWeight.SemiBold)

            }



            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                repeat(3) {
                    var color = when(it) {
                        0 -> { Color(0xFFB81D13)}
                        1 -> {Color(0xFFEFB700)}
                        2 -> {Color(0xFF008450)}
                        else -> {Color.Black}
                    }
                    var filterColorRed = foodFilter.filter {
                        it.hexColor.contains("1D13")

                    }
                    var filterColorOrange = foodFilter.filter {
                        it.hexColor.contains("B700")

                    }
                    var filterColorGreen     = foodFilter.filter {
                        it.hexColor.contains("450")

                    }
                    var maxSize = maxOf(1, filterColorRed.size, filterColorGreen.size, filterColorOrange.size)

                    Spacer(modifier = Modifier.width(15.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {

                        Column(verticalArrangement = Arrangement.Bottom, horizontalAlignment = Alignment.CenterHorizontally) {
                            if(0 == it) {
                                Spacer(modifier = Modifier
                                    .width(80.dp)
                                    .height( (150.dp/maxSize) * filterColorRed.size)
                                    .background(color))
                            } else if(1 == it) {
                                Spacer(modifier = Modifier
                                    .width(80.dp)
                                    .height((150.dp/maxSize) * filterColorOrange.size)
                                    .background(color))
                            }
                            else if (2 == it) {
                                Spacer(modifier = Modifier
                                    .width(80.dp)
                                    .height((150.dp/maxSize) * filterColorGreen.size)
                                    .background(color))
                            }

                           // Text(text = foodFilter.filter { })
                        }
                    }
                }

            }
            
            Spacer(modifier = Modifier.height(20.dp))
            if(foodFilter.size == 0) {
                Text(text = "There are currently no items that has been added")
            } else {
                LazyColumn() {

                    items(foodFilter.size) {
                        var dialog by remember {mutableStateOf(false)}
                        Row(verticalAlignment = Alignment.CenterVertically,modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()
                            .clickable {
                                dialog = true
                            }, horizontalArrangement = Arrangement.SpaceBetween) {
                            Column(verticalArrangement = Arrangement.spacedBy(10.dp )) {
                                Text(text = "${foodFilter[it   ].foodName}", fontWeight = FontWeight.SemiBold)
                                Text(text =  "Updated on: ${FormatDateTime(foodFilter[it].unixDate)}", color= Color.Gray)
                            }
                            Row(horizontalArrangement = Arrangement.Center,modifier = Modifier
                                .width(90.dp)
                                .background(
                                    Color(android.graphics.Color.parseColor("${foodFilter[it].hexColor.removeSuffix("}")}")),
                                    RoundedCornerShape(20.dp)
                                )) {
                                Text(text = "${foodFilter[it].calories}Cal", color = Color.White)
                            }
                        }
                        val scope = rememberCoroutineScope()
                        if(dialog) {

                            Dialog(onDismissRequest = { dialog = false }) {
                                Column(modifier = Modifier
                                    .size(200.dp, 170.dp).background(Color.White)
                                    .padding(10.dp)) {
                                    Text(text = "Delete Item?", fontWeight = FontWeight.SemiBold)
                                    Text(text = "${foodFilter[it].foodName}")
                                    Row(modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 10.dp), horizontalArrangement = Arrangement.End) {
                                        TextButton(onClick = { dialog = false }) {
                                            Text(text = "Cancel")
                                        }

                                        TextButton(onClick = {
                                            scope.launch {
                                                database.write {
                                                    delete(this.query<FoodDbData>("id == $0","${foodFilter[it].id}" ).find())

                                                }
                                                dialog = false
                                            }


                                        }) {
                                            Text(text = "Confirm")
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


}