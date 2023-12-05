package com.example.international

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.international.ui.theme.HotPink

@Composable
fun Header(text: String) {
    Column {
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = text, fontWeight = FontWeight.Black, fontSize = 30.sp)
        Spacer(modifier = Modifier.height(10.dp))
        Divider(
            thickness = 5.dp, color = HotPink, modifier = Modifier.width(16.dp)
        )
    }

}