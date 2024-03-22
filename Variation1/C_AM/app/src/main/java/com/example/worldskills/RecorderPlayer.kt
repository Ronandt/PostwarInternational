package com.example.worldskills

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import androidx.core.net.toUri
import java.io.File

class RecorderPlayer(private val context: Context) {
    var mediaRecorder: MediaPlayer? = null
    fun play(file: File) {
        MediaPlayer.create(context,file.toUri() ).apply {
            mediaRecorder.start()
            mediaRecorder = this
        }
    }

    fun stop() {

        mediaRecorder?.stop()
        mediaRecorder?.release()
        mediaRecorder = null
    }


}