package com.example.worldskillsa_am_2

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import java.io.File
import java.io.FileOutputStream

class Recorder(private val context: Context) {
    var recorder: MediaRecorder? = null
    @RequiresApi(Build.VERSION_CODES.S)
    fun recordAudio(file: File) {
        MediaRecorder(context).apply {
            setAudioSource(MediaRecorder.AudioSource.MIC   )
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(FileOutputStream(file).fd)
            this.prepare()
            this.start()
            recorder = this
        }
    }

    fun stopAudio() {
        recorder?.stop()
        recorder?.release()

        recorder = null
    }
}

class Player(private val context: Context) {
    var player: MediaPlayer? = null
    fun playAudio(file: File) {
        MediaPlayer.create(context, file.toUri()).apply {


        this.start()
            player = this
        }
    }
}