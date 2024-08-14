package com.one.onens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue


@Composable
fun SearchScreen(navController: NavController) {
    var search by remember { mutableStateOf("") }
    Column(modifier = Modifier.padding(10.dp    )) {
        IconButton(onClick = { navController.navigateUp() }) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "")
            
        }

        OutlinedTextField(value = search, onValueChange = {search = it}, modifier = Modifier.fillMaxWidth(), label = {
            Text(text = "What are you loooking",)
        }, leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
        })


    }
    
}