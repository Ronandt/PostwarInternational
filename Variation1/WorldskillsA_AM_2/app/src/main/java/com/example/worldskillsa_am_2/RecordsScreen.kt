package com.example.worldskillsa_am_2

import android.graphics.drawable.Icon
import android.os.Build
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.util.UUID

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun RecordsScreen(navController: NavController) {
    val context = LocalContext.current
    var tempFile = File(context.filesDir,"tempAudio")
    val recorder = remember { Recorder(context)}
    var recording by remember {mutableStateOf(false)}
    var requestPermission = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission() ) {
        
    }
    var change by remember {mutableStateOf(false)}
    var dialog by remember {mutableStateOf(false)}
    var getAudioFile2 = remember {mutableStateListOf<File>()}
    var getAudioFiles =  context.getExternalFilesDir(Environment.DIRECTORY_RECORDINGS)?.listFiles()
    LaunchedEffect(Unit) {
        requestPermission.launch(android.Manifest.permission.RECORD_AUDIO)

    }
    LaunchedEffect(change) {
        delay(2000)
    }
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Records", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(20.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(10.dp)) {
                Button(onClick = {

                                recorder.recordAudio(tempFile)
                    recording = true
                }, modifier = Modifier.fillMaxWidth(), enabled = !recording) {
                    Text(text = "Voice record")
                }

                Button(onClick = { Player(context).playAudio(file = tempFile) }, modifier = Modifier.fillMaxWidth(), enabled = !recording) {
                    Text(text = "Voice Play")
                }

                if(recording) {
                    Button(onClick = { recorder.stopAudio()
                                     recording = !recording
                                     }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Stop")
                    }
                } else {
                    val scope = rememberCoroutineScope()
                    Button(onClick = {
                     scope.launch {
                         var createFile = File(context.getExternalFilesDir(Environment.DIRECTORY_RECORDINGS), UUID.randomUUID().toString())
                         tempFile.copyTo(createFile)
                         dialog = true
                         delay(2000L)
                         change = !change
                     }

                    }, modifier = Modifier.fillMaxWidth()) {
                        Text(text = "Submit")
                    }
                }


            }

        }
        if(dialog) {
            AlertDialog(onDismissRequest = { dialog = false }, confirmButton = { dialog = false }, title = { Text(
                text = "Audio file submitted successfully "
            )}) 
        }
        Spacer(modifier = Modifier.height(20.dp))
Text(text = "Audio list ")
        LazyColumn(verticalArrangement = Arrangement.spacedBy(10.dp )) {
            getAudioFiles?.size?.let {
                items(it) {
                    Card {
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .height(80.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                            getAudioFiles[it]?.let { it1 -> Text(text = it1.name) }
                            IconButton(onClick = {

                                Player(context).playAudio(getAudioFiles[it])
                            }) {
                                androidx.compose.material3.Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play ite," )
                            }
                        }
                    }
                }
            }
        }
    }
}