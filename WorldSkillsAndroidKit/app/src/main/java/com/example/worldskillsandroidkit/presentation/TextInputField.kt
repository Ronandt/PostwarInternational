package com.example.worldskillsandroidkit.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextInputField(state: String, onValueChange: (String) -> Unit, label: String) {
    OutlinedTextField(value = state, onValueChange = onValueChange, modifier = Modifier.fillMaxWidth(), label = {Text(label)})
}