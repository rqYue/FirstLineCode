package com.rq.playvedio

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uri = Uri.parse("android.resource://$packageName/${R.raw.video}")
        videoView.setVideoURI(uri)

        playBtn.setOnClickListener {
            if(!videoView.isPlaying) {
                videoView.start()
            }
        }

        pauseBtn.setOnClickListener {
            if(videoView.isPlaying) {
                videoView.pause()
            }
        }

        replayBtn.setOnClickListener {
            if(videoView.isPlaying) {
                videoView.resume()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.suspend()
    }

}