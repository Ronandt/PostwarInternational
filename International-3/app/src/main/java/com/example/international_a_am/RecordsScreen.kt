package com.example.international_a_am

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.Settings
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import aws.sdk.kotlin.services.s3.model.Object
import aws.smithy.kotlin.runtime.content.asByteStream
import com.example.international_a_am.Recorder.Player
import com.example.international_a_am.Recorder.Recorder
import com.example.international_a_am.data.S3Bucket
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.File
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


enum class RecordState {
    Recording,
    NotPlaying
}
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun RecordsScreen(navController: NavController) {
    val context = LocalContext.current
    val tempFile = File(context.cacheDir, "audiotemp.mp3")
    var recordedBefore by remember {mutableStateOf(false)}
    val recorder =  Recorder(context)
    val scope = rememberCoroutineScope()
    var recordingState by remember {mutableStateOf(RecordState.NotPlaying
    )}
    var dialog by remember {mutableStateOf(false)}
    var pressed by remember {mutableStateOf(false)}
    val player = Player(context)
    LaunchedEffect(key1 = Unit) {

    }
    var eff = rememberLauncherForActivityResult(contract = ActivityResultContracts.RequestPermission()) {
        if(!it && pressed) {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            val uri = Uri.fromParts("package", context.getPackageName(), null)
            intent.data = uri
            context.startActivity(intent)
            pressed = false
        }
    }
    var loading by remember {mutableStateOf(true)}
    var files = remember {mutableStateListOf<Object>()}
    //var files = remember {context.getExternalFilesDir(Environment.DIRECTORY_MUSIC)?.listFiles() }
    var change by remember {mutableStateOf(false)}
    var playing by remember {mutableStateOf<String?>(null)}
    LaunchedEffect(change) {
        loading = true
        eff.launch(android.Manifest.permission.RECORD_AUDIO)
        //eff.launch(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        //eff.launch(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        files.clear()
        S3Bucket().listItems()?.filter { it.key?.contains("audio")!!}?.let { files.addAll(it) }
        loading = false

    }

Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
    Text(text = "Records", fontSize = 30.sp, fontWeight = FontWeight.Medium)
    Spacer(modifier = Modifier.height(20.dp))

    Card(modifier = Modifier.fillMaxWidth() ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                if(recordingState == RecordState.NotPlaying) {
                    Button(shape = RoundedCornerShape(10.dp), colors = ButtonDefaults.buttonColors(Color.Black),onClick = {
                        if (ContextCompat.checkSelfPermission(context, android.Manifest.permission.RECORD_AUDIO) !=
                            PackageManager.PERMISSION_GRANTED
                        ) {
                            pressed = true
                            eff.launch(android.Manifest.permission.RECORD_AUDIO)
                            println("why1")

                        } else {
                            recorder.record(tempFile)
                            recordingState =  RecordState.Recording
                            println("why2")

                        }


                        println("why")


                    }, modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .width(200.dp)) {
                        Text(text = "Voice Record")
                    }
                } else {
                    Button(shape = RoundedCornerShape(10.dp),colors = ButtonDefaults.buttonColors(containerColor = Color.Black),onClick = {
                        recorder.stop()
                        recordingState =  RecordState.NotPlaying
                        recordedBefore = true
                        playing = null
                        println("why")


                    }, modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .width(200.dp)) {
                        Text(text = "End Recording")
                    }
                }

                Button(shape = RoundedCornerShape(10.dp),colors = ButtonDefaults.buttonColors(containerColor = Color.Black),onClick = {
                    try {
                        player.play(tempFile)
                        playing = tempFile.name
                    }
                    catch (e: Exception) {

                    }

                }, modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .width(200.dp), enabled = recordingState == RecordState.NotPlaying) {
                    Text(text = "Voice Play")
                }
            }

            Button(shape = RoundedCornerShape(10.dp),colors = ButtonDefaults.buttonColors(containerColor = Color.Black),onClick = {
                try {
                    val dateFormat = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
                    val currentTimeFormatted = dateFormat.format(Date())

                    scope.launch {
                        S3Bucket().upload(tempFile.asByteStream(), "audio-$currentTimeFormatted")
                        delay(1000)
                        change = !change
                    }
                    recordedBefore = false

                    dialog = true
                }
                catch (e: Exception) {

                }



            }, modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.End)
                .clip(RoundedCornerShape(10.dp)),  enabled = recordingState == RecordState.NotPlaying && recordedBefore
            ) {
                Text(text = "Submit")
            }
        }

    }
    Spacer(modifier = Modifier.height(20.dp))

    LazyColumn(modifier = Modifier.padding(horizontal = 12.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        item {
            Spacer(modifier = Modifier.height(10.dp))
        }
        if(loading) {
            item {
                CircularProgressIndicator()
            }

        } else {
            files?.size?.let {

                items(it) {
                    Card(modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)){
                        Row(modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(10.dp)
                            .clickable {
                                scope.launch {

                                    files[it].key
                                        ?.let { it1 ->
                                            S3Bucket().getItems(
                                                context = context,
                                                keyN = it1
                                            )
                                        }
                                        ?.let { it2 -> player.play(it2) }

                                }


                            }, horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                            files[it].key?.let { it1 -> Text(text = it1, fontWeight = FontWeight.Medium, fontSize = 18.sp, maxLines = 1, modifier = Modifier
                                .width(300.dp)
                                .basicMarquee()) }
                            IconButton(onClick = { /*TODO*/ }, colors = IconButtonDefaults.iconButtonColors(containerColor = Color.Black)) {
                                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = "Play recording ", tint = Color.White)
                            }


                        }
                    }

                }
            }
        }



    }

    if(dialog) {

    }



}
}