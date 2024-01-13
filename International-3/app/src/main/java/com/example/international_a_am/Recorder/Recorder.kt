package com.example.international_a_am.Recorder

import android.content.Context
import android.media.MediaRecorder
import java.io.File

class Recorder(private val context: Context) {
    var recorder: MediaRecorder? = null

    fun record(file: File) {
        MediaRecorder(context).apply {


            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(file)

            prepare()
            start()
            recorder = this
        }


    }

    fun stop() {
        recorder?.apply {
            stop()
            release()
            recorder = null

        }
    }
}