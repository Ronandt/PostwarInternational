package com.example.shoepanic.ui.theme

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Header(text: String) {
    Text(text = text, fontWeight = FontWeight.SemiBold, color = LightningBlue, fontSize = 20.sp  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AuthenticationTextField(value: String, onValueChange: (String) -> Unit, leadingIcon: @Composable () -> Unit, placeholder: String, visualTransformation: Boolean = false, modifier: Modifier = Modifier)  {
    OutlinedTextField(value = value, onValueChange = onValueChange, modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp)
        .then(modifier), visualTransformation = if(visualTransformation)PasswordVisualTransformation() else VisualTransformation.None,leadingIcon = leadingIcon, label = { Text(
        text = placeholder
    )})

}

@Composable
fun MainButton(text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(onClick = onClick, modifier = Modifier
        .fillMaxWidth()
        .padding(bottom = 10.dp).then(modifier), colors = ButtonDefaults.buttonColors(containerColor = DarkBlue)) {
        Text(text = text, fontWeight = FontWeight.SemiBold, color = Color.White, fontSize = 18.sp)
    }
}