package com.example.worldskillsandroidkit.Practice

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.media.MediaRecorder.AudioEncoder
import android.media.MediaRecorder.AudioSource
import android.media.MediaRecorder.OutputFormat
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import java.io.File

class RecorderPractice2(private val context: Context) {
    var mediaRecorder: MediaRecorder? = null
    @RequiresApi(Build.VERSION_CODES.S)
    fun record(file: File) {
        mediaRecorder = MediaRecorder(context).apply {
            this.setAudioSource(AudioSource.MIC)
            this.setOutputFormat(OutputFormat.MPEG_4)
            this.setAudioEncoder(AudioEncoder.AAC)
            this.setOutputFile(file.outputStream().fd)
            this.prepare()
            this.start()
        }

    }

    fun stop() {
        mediaRecorder.apply {
            this?.stop()
            this?.release()
            mediaRecorder = null
        }
    }
}

class PlayerPractice2(private val context: Context) {
    var player: MediaPlayer? = null
    fun play(file: File) {
        player = MediaPlayer.create(context, file.toUri()).apply {
            this.prepare()
            this.start()
        }

    }

}