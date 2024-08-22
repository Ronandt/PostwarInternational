package edu.ws2022.aXX.am

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AppColumn(content:  @Composable (scope: ColumnScope) ->Unit ) {
    Column(modifier = Modifier.fillMaxSize().padding(10.dp    ), horizontalAlignment = Alignment.CenterHorizontally) {
        content(scope =  this )
    }
}

@Composable
fun Header(title: String) {
    Text(text = title, fontSize = 24.sp)
}