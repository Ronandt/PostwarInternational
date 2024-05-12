package com.example.worldskillsandroidkit.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TextInputField(state: String, onValueChange: (String) -> Unit, label: String) {
    Column(modifier = Modifier.semantics { testTagsAsResourceId = true }){
        OutlinedTextField(value = state, onValueChange = onValueChange, modifier = Modifier.fillMaxWidth().testTag("w"), label = {Text(label)})
    }

}