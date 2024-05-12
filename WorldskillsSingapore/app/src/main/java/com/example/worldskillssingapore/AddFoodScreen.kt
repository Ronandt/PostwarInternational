package com.example.worldskillssingapore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.worldskillssingapore.ui.theme.RedColor
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFoodScreen(navController: NavController, id: String?, calories:String, name: String, colorScheme: String) {
    var foods = remember { mutableStateListOf<Food>()}
    var foodName by remember {mutableStateOf("${name}")}
    var calories by remember {mutableStateOf("${calories}")}
    var dateConsumedRaw by remember { mutableStateOf(0L)}

   var AdatePickerState = rememberDatePickerState()
    var datePickerState by remember {mutableStateOf(false)}
    val scope = rememberCoroutineScope()

    Scaffold(topBar = {
        CenterAlignedTopAppBar(title = { Text(text = "Add Food", color = Color.White, fontWeight = FontWeight.SemiBold) }, navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White )
            }

        }, colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = RedColor))

    }) {
        Column(modifier = Modifier
            .padding(it)
            .padding(20.dp)) {
            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(value =foodName    , onValueChange = {foodName = it}, enabled = false,modifier = Modifier.fillMaxWidth())
                OutlinedTextField(value = calories, onValueChange = {calories = it} , enabled = false,modifier = Modifier.fillMaxWidth())
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .clickable {

                        datePickerState = true
                    }) {
                    OutlinedTextField(enabled = false,label = { Text(text = "Enter date consumed")},value =FormatDateTime(dateConsumedRaw), onValueChange = {}, modifier = Modifier
                        .fillMaxWidth()
                    )
                }

                if(datePickerState) {
                    DatePickerDialog(onDismissRequest = { datePickerState  = false}, confirmButton = {
                        Button(onClick = {
                            println(AdatePickerState.selectedDateMillis.toString())
                            dateConsumedRaw =
                            AdatePickerState.selectedDateMillis!!
                            datePickerState = false
                        }) {
                            Text(text = "Select Date")
                        }

                    }) {
                        DatePicker(state = AdatePickerState)
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = {
                    scope.launch {
                        val suf = "#$colorScheme".removeSuffix("}")
                        FoodDB.getDb().write {
                        val food =     FoodDbData().apply {
                                this.foodName = foodName
                                if (id != null) {
                                    this.id = id
                                }
                                this.hexColor = suf
                                this.unixDate = dateConsumedRaw
                                this.calories = calories


                            }

                            copyToRealm(food)

                        }

                        navController.navigateUp()
                    }



                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Submit", color = Color.White)
                }

            }
        }
    }
}

fun FormatDateTime(unixTime: Long): String {
    var dateFormat = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.ENGLISH)
    return dateFormat.format(Date(unixTime))

}