package com.example.myapplication.presentation

import android.Manifest.permission.RECORD_AUDIO
import android.media.MediaRecorder
import android.widget.Space
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.myapplication.recorder.AudioRecorder
import com.example.myapplication.recorder.Player
import java.io.File
import java.util.UUID


enum class RecordingState {
    Recording,
    None,

}
@Composable
fun RecordsScreen(navController: NavController) {

    val context = LocalContext.current
    var playingState by remember {mutableStateOf(RecordingState.None)}
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
        if(!it) Toast.makeText(context, "You need to accept permissions in order to record audio!", Toast.LENGTH_LONG).show()
    }
     val recorder by lazy {
        AudioRecorder(context = context)
    }
     val player by lazy {
        Player(context)
    }

    var audioFile: File? = null


    LaunchedEffect(key1 = Unit ) {
        launcher.launch(RECORD_AUDIO)
    }
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .fillMaxSize()
        .padding(10.dp)) {
        Text(text = "Records", fontSize = 24.sp )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedCard(modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()) {
            if( playingState == RecordingState.None) {
                Button(onClick = { recorder.start(File(context.cacheDir, "audio-${UUID.randomUUID()}.mp3").also {
                    recorder.start(it)
                    audioFile = it
                    playingState = RecordingState.Recording
                }) }) {
                    Text(text = "Voice Record")

                }
            } else {
                Button(onClick = { recorder.stop() }) {
                    Text(text = "Stop recording")
                    playingState = RecordingState.None
                }
            }


            Button(onClick = {

                player.playFile(audioFile?:return@Button

            ) }, enabled = (playingState!= RecordingState.Recording)) {
                Text(text = "Voice play")

            }
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = { /*TODO*/ }, modifier = Modifier.align(Alignment.End), enabled = (playingState!= RecordingState.Recording)) {
                Text(text = "Submit")

            }
        }

        Spacer(modifier = Modifier.height(20.dp))
        OutlinedCard(modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)) {
            Text(text = "Audios list", fontSize = 18.sp)

        }
    }

}