package com.rq.context

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import java.time.Duration

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        "This is Toast".showToast()
    }

    // 使用全局获取的 context
    fun String.showToast(duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(MyApplication.context, this, duration).show()
    }

    fun Int.showToast(duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(MyApplication.context, this, duration).show()
    }

}