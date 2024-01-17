package com.example.scenario2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.scenario2.ui.theme.appBarColor
import com.example.scenario2.ui.theme.listItemsColor
import com.example.scenario2.ui.theme.primaryButtonColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckoutScreen(navController: NavController) {

Scaffold(topBar = {
    TopAppBar(actions = {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = "Checkout")
                        }
    },title = { Text(text = "Checkout", fontWeight = FontWeight.Bold) }, colors = TopAppBarDefaults.topAppBarColors(containerColor = appBarColor), navigationIcon = {
        IconButton(onClick = { navController.navigateUp()}) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Go back")
            }
    })
}) {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(it)
        .padding(horizontal = 18.dp)){
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp)){
            items(3) {

                androidx.compose.material3.Card(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                    onClick = { /*TODO*/ },
                    colors = CardDefaults.cardColors(containerColor = listItemsColor)
                ) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Image(painter = painterResource(R.drawable.bubbletea), contentDescription = "")
                        Column {
                            Text(text = "Bubble milk tea with ice cream!", fontSize = 12.sp)
                            Text(text = "Medium sized, vanilla", fontSize = 12.sp)

                        }
                        Column {
                            Text(text = "$7.6", fontWeight = FontWeight.SemiBold)
                            SelectionButton(text = "Edit", selected = true, small = true) {

                            }
                        }


                    }
                }

            }
        }
        Spacer(modifier = Modifier.height(50.dp))

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Sub-total", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = "17.2", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Sub-total", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = "17.2", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(text = "Sub-total", fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Text(text = "17.2", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.weight(1f))
       androidx.compose.material.Button(onClick = { /*TODO*/ },modifier = Modifier.width(180.dp).align(Alignment.CenterHorizontally), colors = androidx.compose.material.ButtonDefaults.buttonColors(backgroundColor = primaryButtonColor)) {
            Text(text = "PAY", color = Color.Black)
        }
    }
    }

}