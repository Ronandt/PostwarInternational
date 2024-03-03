package com.example.worldskillsandroidkit.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun GridList(rowLength: Int, listSize: Int ,contentCallback: (Int) -> Unit ,modifier: Modifier = Modifier) {
    FlowRow(maxItemsInEachRow = rowLength, horizontalArrangement = Arrangement.spacedBy(10.dp), modifier = modifier) {
        repeat(listSize) {
            contentCallback(it)
        }

    }
}