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
import java.lang.reflect.Field

class Recorder(private val context: Context) {
    var recorder: MediaRecorder? = null
    @RequiresApi(Build.VERSION_CODES.S)
    fun record(file: File) {
        MediaRecorder(context).apply {
            this.setAudioSource(AudioSource.MIC)
            this.setOutputFormat(OutputFormat.MPEG_4)
            this.setAudioEncoder(AudioEncoder.AAC)
            this.setOutputFile(file.outputStream().fd)
            prepare()
            start()
            recorder = this
        }
    }

    fun stop() {
        recorder.apply {
            this?.stop()
            this?.release()
            recorder = null
        }
    }
}


class Player(private val context: Context) {
    var player: MediaPlayer? = null

    @RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
    fun play(file: File) {
        MediaPlayer.create(context, file.toUri()).apply {
            prepare()
            start()
            player = this
        }
    }

}