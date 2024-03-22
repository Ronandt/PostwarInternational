@file:OptIn(ExperimentalFoundationApi::class)

package com.example.draganddrop

import android.content.ClipData
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.draganddrop.dragAndDropSource
import androidx.compose.foundation.draganddrop.dragAndDropTarget
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draganddrop.DragAndDropEvent
import androidx.compose.ui.draganddrop.DragAndDropTarget
import androidx.compose.ui.draganddrop.DragAndDropTransferData
import androidx.compose.ui.draganddrop.toAndroidDragEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.draganddrop.ui.theme.DragAndDropTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DragAndDropTheme {
                // A surface container using the 'background' color from the theme
                Column {
                    DraggableList()
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DragAndDropTheme {
        Greeting("Android")
    }
}

@Composable
fun DraggableList() {
    var listConcept = remember {mutableStateListOf<String>("Blue", "Red", "Green", "Black")}
    var backgroundColor = remember {mutableStateOf(Color.Gray)}
   LazyColumn {
       items(listConcept.size) {index ->
           Card(modifier = Modifier
               .height(100.dp)
               .fillMaxWidth()  .dragAndDropTarget(shouldStartDragAndDrop = {
                   true
//fcdcc

               }, target = object : DragAndDropTarget {
                   override fun onDrop(event: DragAndDropEvent): Boolean {
                       val initalData =
                           listConcept[event.toAndroidDragEvent().clipData.getItemAt(0).intent.getIntExtra(
                               "ItemInfo",
                               0
                           )]
                       listConcept[event.toAndroidDragEvent().clipData.getItemAt(0).intent.getIntExtra(
                           "ItemInfo",
                           0
                       )] = listConcept[index]
                       listConcept[index] = initalData
                       return true
                   }

               })
               .dragAndDropSource {
                   detectTapGestures(onLongPress = {
                       this.startTransfer(
                           transferData = DragAndDropTransferData(
                               clipData = ClipData.newIntent(
                                   "action_drag",
                                   Intent().apply {
                                       this.putExtra("ItemInfo", index)
                                   })
                           )
                       )
                   })

               }
            ) {
               Text(listConcept[index])
           }
       }

   }
}



@Composable
fun DraggableComponent() {

        Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "Ic launcher background", contentScale = ContentScale.Crop, modifier = Modifier
            .size(130.dp)
            .dragAndDropSource(drawDragDecoration = {
                this.drawCircle(Color.Black, radius = 100f)

            }) {
                this.detectTapGestures(onLongPress = {
                    this.startTransfer(DragAndDropTransferData(
                        clipData = ClipData.newIntent(
                            "foodItem",
                            Intent("action_foodItem").apply {
                                putExtra("data_foodItem", 4)

                            }
                        )
                    ))
                })
            })

}


@Composable
fun DropTarget() {
   // var dragged by remember { mutableStateOf(false  )}
    var backgroundColor by remember {mutableStateOf(Color.Green)}
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(backgroundColor)
            .dragAndDropTarget(
                shouldStartDragAndDrop = { startEvent -> return@dragAndDropTarget true },
                target = object : DragAndDropTarget {
                    override fun onDrop(event: DragAndDropEvent): Boolean {
                        if (event.toAndroidDragEvent().clipData.getItemAt(0).intent.getIntExtra(
                                "data_foodItem",
                                0
                            ) == 4
                        ) {
                            backgroundColor = Color.Red
                        }
                        return true
                    }

                    override fun onEntered(event: DragAndDropEvent) {
                        backgroundColor = Color.Blue
                        super.onEntered(event)
                    }

                    override fun onExited(event: DragAndDropEvent) {
                        backgroundColor = Color.Green
                        super.onExited(event)
                    }
                }

            )
    ) { /*  Content of the drag target composable */ }
}