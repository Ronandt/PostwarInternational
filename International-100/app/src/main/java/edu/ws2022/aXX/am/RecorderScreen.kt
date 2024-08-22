package edu.ws2022.aXX.am

import android.os.Build
import android.os.Environment
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.io.File
import java.util.UUID

enum class RecorderState {
    Recording,
    Finished,
    Default ,
}

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun RecorderScreen(navController: NavController) {
    var recorderState by remember {mutableStateOf<RecorderState>(RecorderState.Default)}
    val context = LocalContext.current
    val temp = File(context.getExternalFilesDir(Environment.DIRECTORY_PODCASTS), "/tempF")
    val recorder = remember {Recorder(context = context)}
    val fileSources = remember { mutableStateListOf<File>()}
    var toggle by remember {mutableStateOf(false)}
    var recorderPermis = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission() ) {
        if(!it) {

        }
    }

    LaunchedEffect(toggle   ) {
        fileSources.clear()
        context.getExternalFilesDir(Environment.DIRECTORY_PODCASTS)?.listFiles()?.toList()
            ?.let { fileSources.addAll(it) }
    }

    LaunchedEffect(Unit) {
        recorderPermis.launch(android.Manifest.permission.RECORD_AUDIO)

    }
    AppColumn {
        Header(title = "Records")

        Card(modifier = Modifier.fillMaxWidth()) {
            if(recorderState == RecorderState.Recording) {
                Button(onClick = {
                    recorderState = RecorderState.Finished
                    recorder.stop()


                }, modifier = Modifier.fillMaxWidth()) {
                    Text(text = "Finish Recording")
                }
            } else {
                Button(onClick = {
                    recorderState = RecorderState.Recording
                    recorder.record(temp)


                }, modifier = Modifier.fillMaxWidth(), enabled = recorderState == RecorderState.Default) {
                    Text(text = "Voice record")
                }
            }

            Button(onClick = {
                recorder.playAudio(file = temp)

            }, modifier = Modifier.fillMaxWidth(), enabled = recorderState == RecorderState.Finished) {
                Text(text = "Voice play")
            }
            Button(onClick = { val file = File(context.getExternalFilesDir(Environment.DIRECTORY_PODCASTS), "/${UUID.randomUUID()}-recording")
                             temp.copyTo(file)
                recorderState = RecorderState.Default
                toggle = !toggle
                             }, modifier = Modifier.fillMaxWidth(), enabled = recorderState == RecorderState.Finished) {
                Text(text = "Submit")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn( ) {
            item{
                Text(text = "Audios list", fontSize = 18.sp)
            }
            items(fileSources.size) {
                if(!fileSources[it].name.contains("temp")) {
                    Row(modifier = Modifier
                        .padding(vertical = 10.dp)
                        .fillMaxWidth().clickable {
                            recorder.playAudio(fileSources[it])
                        }) {
                        Text(text = "${fileSources[it].name}")
                        Icon(Icons.Default.KeyboardArrowRight, contentDescription = "Arrow" )
                    }
                }

            }

        }
    }
}