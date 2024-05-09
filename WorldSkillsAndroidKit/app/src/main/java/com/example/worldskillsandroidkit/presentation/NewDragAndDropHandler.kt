package com.example.worldskillsandroidkit.presentation
/*
import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.unit.dp



@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DragAndDropHandler() {
    var listChange =  remember {mutableStateListOf<String>("1", "fe", "few", "wf", "fw")}

    LazyColumn {
        items(listChange.size) {
            Row(modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .dragAndDropTarget(shouldStartDragAndDrop = { startEvent ->
                    true
                }, target = object : DragAndDropTarget {
                    override fun onDrop(event: DragAndDropEvent): Boolean {
                        println(event.toAndroidDragEvent().clipData.description.label)
                        listChange[event.toAndroidDragEvent().clipData.description.label.toString().toInt()] = listChange[it].also { a->
                            listChange[it] =  listChange[event.toAndroidDragEvent().clipData.description.label.toString().toInt()]
                        }

                        return true
                    }
                })
                .dragAndDropSource {
                    this.startTransfer(
                        transferData = DragAndDropTransferData(
                            clipData = ClipData.newPlainText(
                                "$it",
                                ""
                            )
                        )
                    )
                }) {
                Text(text = listChange[it])
            }
        }

    }
}*/