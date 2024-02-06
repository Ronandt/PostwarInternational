package com.example.international_a_am

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import aws.sdk.kotlin.services.s3.model.Object
import aws.smithy.kotlin.runtime.content.asByteStream
import com.example.international_a_am.Recorder.Player
import com.example.international_a_am.Recorder.Recorder
import com.example.international_a_am.data.S3Bucket
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID

enum class RecordState {
    Recording,
    NotPlaying
}
@Composable
fun RecordsScreen(navController: NavController) {
    val context = LocalContext.current
    val tempFile = File(context.cacheDir, "audiotemp.mp3")
    val recordedBefore by remember {mutableStateOf(false)}
    val recorder =  Recorder(context)
    val scope = rememberCoroutineScope()
    var recordingState by remember {mutableStateOf(RecordState.NotPlaying
    )}
    var dialog by remember {mutableStateOf(false)}

    val player = Player(context)
    var eff = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {

    }
    var files = remember {mutableStateListOf<Object>()}
    //var files = remember {context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)?.listFiles() }
    var change by remember {mutableStateOf(false)}
    LaunchedEffect(change) {
        eff.launch(android.Manifest.permission.RECORD_AUDIO)
        eff.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        eff.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        files.clear()
        S3Bucket().listItems()?.filter { it.key?.contains("audio")!!}?.let { files.addAll(it) }

    }

Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
    Text(text = "Records", fontSize = 30.sp)
    Spacer(modifier = Modifier.height(20.dp))

    Card(modifier = Modifier.fillMaxWidth()) {
        if(recordingState == RecordState.NotPlaying) {
            Button(onClick = {
               recorder.record(tempFile)
                recordingState =  RecordState.Recording




            }, modifier = Modifier.align(Alignment.Start)) {
                Text(text = "Voice Record")
            }
        } else {
            Button(onClick = {
                recorder.stop()
                recordingState =  RecordState.NotPlaying




            }, modifier = Modifier.align(Alignment.Start)) {
                Text(text = "End Recording")
            }
        }

        Button(onClick = {
                         player.play(tempFile)
        }, modifier = Modifier.align(Alignment.Start), enabled = recordingState == RecordState.NotPlaying) {
            Text(text = "Voice Play")
        }
        Button(onClick = {
            scope.launch {
                S3Bucket().upload(tempFile.asByteStream(), "audio-" + UUID.randomUUID().toString())
                delay(2000)
                change = !change
            }

            dialog = true


        }, modifier = Modifier.align(Alignment.End),  enabled = recordingState == RecordState.NotPlaying) {
            Text(text = "Submit")
        }
    }
    Spacer(modifier = Modifier.height(20.dp))

    LazyColumn {
        item {
            Text(text = "Audios List")
            Spacer(modifier = Modifier.height(20.dp))
        }
        files?.size?.let {
            items(it) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        scope.launch {
                            files[it].key?.let { it1 -> S3Bucket().getItems(context = context ,keyN = it1) }
                                ?.let { it2 -> player.play(it2) }

                        }


                    }, horizontalArrangement = Arrangement.SpaceBetween) {
                    files[it].key?.let { it1 -> Text(text = it1) }
                    Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play recording ")
                }
            }
        }


    }
    if(dialog) {

    }
}
}