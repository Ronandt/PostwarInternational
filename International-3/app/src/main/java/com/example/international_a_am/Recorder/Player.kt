package com.example.international_a_am.Recorder

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

class Player(private val context: Context) {
    private var player: MediaPlayer? = null
    fun play(file: File, registerCallback: () -> Unit = {}) {
        MediaPlayer.create(context, file.toUri()).apply {
            player = this
            this.setOnCompletionListener { registerCallback() }
            start()

        }
    }

}