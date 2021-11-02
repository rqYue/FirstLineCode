package com.rq.notification

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NotificationActivity  : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        // 点击之后对通知进行关闭
        val manager = getSystemService( Context.NOTIFICATION_SERVICE) as NotificationManager
        // 传入创建通知时指定的 id
        manager.cancel(1)

    }

}