package com.example.shoepanic

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DrawerState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.shoepanic.ui.theme.DarkBlue
import com.example.shoepanic.ui.theme.DarkRed
import com.example.shoepanic.ui.theme.Header
import com.example.shoepanic.ui.theme.LightGray
import com.example.shoepanic.ui.theme.MainButton
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun QueueScreen(navController: NavController) {
    val scope = rememberCoroutineScope()
    var qrCode by remember {mutableStateOf(false)}
    var bottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    ModalBottomSheetLayout(sheetShape = RoundedCornerShape(20.dp),sheetState = bottomSheetState, sheetContent = {

        var waitingTime by remember {mutableStateOf(5)}
        var error by remember {mutableStateOf(false)}
        Column(modifier = Modifier.padding(10.dp)) {
            Header(text = "Settings")
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(value = waitingTime.toString(), onValueChange =  {
                if(it.toIntOrNull() != null) {
                    waitingTime = it.toInt()
                    error = false
                } else {
                    error = true
                }


            }, isError = error, modifier = Modifier.fillMaxWidth(), label = {
                Text(text = "Waiting time (minutes)")
            })
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { scope.launch {
                bottomSheetState.hide()
            }}, colors = ButtonDefaults.buttonColors(containerColor = DarkBlue)) {
                Text(text = "Save", fontWeight = FontWeight.SemiBold, color = Color.White)
            }
        }

    }) {
        Box {
            Column(modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)) {
                IconButton(onClick = { navController.navigateUp() }, Modifier.offset(x = -10.dp)) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription =  "go back")
                }
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween   ) {
                    Column(verticalArrangement = Arrangement.Center) {
                        Header("Main Queue")
                        Text(text = "Manage your main queue today")
                    }
                    IconButton(onClick = {
                        scope.launch {
                            bottomSheetState.show()
                        }
                    }) {
                        Icon(imageVector = Icons.Default.Settings, contentDescription = "Settings" )

                    }




                }
                Spacer(modifier = Modifier.height(20.dp))
                LazyColumn(modifier = Modifier.heightIn(0.dp, 400.dp), verticalArrangement = Arrangement.spacedBy(10.dp )) {
                    items(5) {
                        Card(modifier = Modifier.fillMaxWidth(), colors = CardDefaults.cardColors(
                            LightGray)) {
                            Row(modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {
                                Column {
                                    Text(text = "Jane Doe", fontSize = 13.sp)
                                    Text(text = "Position #$it", fontSize = 13.sp)
                                }
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                                        DarkBlue)) {
                                        Text(text = "Enter Physical", fontWeight = FontWeight.SemiBold, color = Color.White)

                                    }
                                    Button(onClick = { /*TODO*/ }, colors = ButtonDefaults.buttonColors(
                                        DarkRed)) {
                                        Text(text = "Remove", fontWeight = FontWeight.SemiBold, color = Color.White)
                                    }
                                }
                            }
                        }
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                    Icon(painterResource(id = R.drawable.baseline_error_24), contentDescription = "Hint" )
                    Text(text = "Users will automatically be removed when they are accepted into the physical queue", fontSize = 12.sp)

                }
                Spacer(modifier = Modifier.weight(1f))
                MainButton(text = "View QR Codes", onClick = { qrCode = true})


        }
            if(qrCode) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Spacer(modifier = Modifier.fillMaxSize().background(Color(0xAA000000)))
                    Dialog(onDismissRequest = { qrCode = false }) {
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .background(Color.White, shape = RoundedCornerShape(20.dp)), horizontalAlignment = Alignment.CenterHorizontally) {
                            IconButton(onClick = { qrCode = false}, modifier = Modifier.align(Alignment.End)) {
                                Icon(imageVector = Icons.Default.Close, contentDescription ="Close" )
                            }
                            Text(text = "Virtual queue", fontWeight = FontWeight.SemiBold)
                            Image(painter = painterResource(R.drawable.baseline_qr_code_scanner_24), contentDescription = "Qr code", modifier = Modifier.size(100.dp))

                            Spacer(modifier = Modifier.height(20.dp))
                            Text(text = "Physical queue", fontWeight = FontWeight.SemiBold)
                            Image(painter = painterResource(R.drawable.baseline_qr_code_scanner_24), contentDescription = "Qr code", modifier = Modifier.size(100.dp))
                        }

                    }
                }

            }

        }
    }



}