package edu.ws2022.aXX.am

import android.content.Context
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.media.MediaRecorder.AudioEncoder
import android.media.MediaRecorder.AudioSource
import android.media.MediaRecorder.OutputFormat
import android.os.Build
import android.os.Environment
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import java.io.File

class Recorder(private val context: Context) {
    var mediaRecorder: MediaRecorder? = null
    @RequiresApi(Build.VERSION_CODES.S)
    fun record(file: File) {
       mediaRecorder =  MediaRecorder(context).apply {
           this.setAudioSource(AudioSource.MIC)

            this.setOutputFormat(OutputFormat.THREE_GPP)
           this.setAudioEncoder(AudioEncoder.AAC)
           this.setOutputFile(file)



           this.prepare()
            this.start()
        }
    }

    fun stop() {
        mediaRecorder.apply {

            this?.stop()
            this?.release()
        }
        mediaRecorder = null
    }

    fun playAudio(file: File) {
        MediaPlayer.create(context, file.toUri()).apply {

             this.start()
        }

    }

}