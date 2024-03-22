package com.example.worldskills_am_b

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TemplateContent(content: @Composable () -> Unit , modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize().padding(10.dp).then(modifier)) {
        content()
    }
}