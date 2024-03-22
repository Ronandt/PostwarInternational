package com.example.worldskillsandroidkit.Practice

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import java.io.File

class Recorder2(private val context: Context) {
    private var mediaRecorder: MediaRecorder? = null
    @RequiresApi(Build.VERSION_CODES.S)
    fun recorder(file: File) {
        MediaRecorder(context).apply{
            this.setAudioSource(MediaRecorder.AudioSource.MIC)
            this.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            this.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            this.setOutputFile(file.outputStream().fd)
            this.prepare()
            this.start()
            mediaRecorder = this
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

class Player2(private val context: Context) {
    private var mediaPlayer: MediaPlayer? = null
    @RequiresApi(34)
    fun play(file: File) {
        MediaPlayer.create(context, file.toUri()).apply {
            this.prepare()
            this.start()
            mediaPlayer = this
        }

    }
}