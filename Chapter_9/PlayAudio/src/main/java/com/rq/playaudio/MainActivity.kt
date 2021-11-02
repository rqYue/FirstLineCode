package com.rq.playaudio

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initMediaPlayer()

        playBtn.setOnClickListener {
            if(!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
        }

        pauseBtn.setOnClickListener {
            if(mediaPlayer.isPlaying) {
                mediaPlayer.pause()
            }
        }

        stopBtn.setOnClickListener {
            if(mediaPlayer.isPlaying){
                mediaPlayer.reset()
                initMediaPlayer()
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    private fun initMediaPlayer() {
        val assetManager = assets
        val fd = assetManager.openFd("music.mp3")
        mediaPlayer.setDataSource( fd.fileDescriptor, fd.startOffset, fd.length)
        mediaPlayer.prepare()
    }


    private infix fun <A, B> A.with(that: B) : Pair<A, B> = Pair(this, that)

    val map = mapOf( "Apple" with 1, "Banana" with 2, "Orange" with 3, "Pear" with 4, "Grape" with 5 )

}