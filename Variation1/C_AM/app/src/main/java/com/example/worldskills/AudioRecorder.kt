package com.example.worldskills

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import java.io.File
import java.io.FileOutputStream

/*
class AudioRecorder {
    var audioRecorder = MediaPlayer
}*/

class RecordYes(private val context: Context) {
    var recorder: MediaRecorder? = null
    @RequiresApi(Build.VERSION_CODES.S)
    fun recordThing(file: File) {
        MediaRecorder(context).apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(
                FileOutputStream(file).fd)
            prepare()
            start()
            recorder = this
        }
    }

    fun stopbro() {

        recorder?.stop()
        recorder?.reset()
        recorder = null
    }

}

class AudioPlayerbro(private val context:Context) {
    var player: MediaPlayer?  = null
    fun playThing(file: File) {
        MediaPlayer.create(context, file.toUri() ).apply {

        }
    }
}