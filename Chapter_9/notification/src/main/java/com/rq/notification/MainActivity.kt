package com.rq.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val manager = getSystemService( Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 创建 id 为 normal 的渠道，下次创建时，系统将检测到该渠道已经存在，不再重复创建
            val channel = NotificationChannel("normal", "Normal", NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel( channel )
        }

        sendNotice.setOnClickListener {

            val intent = Intent(this, NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, 0)

            // 传入与创建渠道时，相同的id
            val notification = NotificationCompat.Builder( this, "normal")
                    .setContentIntent(pi)
                    .setContentTitle("title")
//                    .setContentText("TextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextText")
                    .setStyle( NotificationCompat.BigTextStyle().bigText( "TextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextTextText"))
                    .setSmallIcon(R.drawable.small_icon)
                    //.setLargeIcon( BitmapFactory.decodeResource(resources, R.drawable.large_icon) )
                    .setStyle(NotificationCompat.BigPictureStyle().bigPicture(
                            BitmapFactory.decodeResource( resources, R.drawable.big_image) ))
                    // 点击之后，通知自动取消
                    .setAutoCancel(true)
                    .build()

            manager.notify(1, notification)
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // 创建重要等级为 NotificationManager.IMPORTANCE_HIGH 的通知
            val channel = NotificationChannel("important", "Important", NotificationManager.IMPORTANCE_HIGH)
            manager.createNotificationChannel(channel)
        }

        setImportance.setOnClickListener {
            val intent = Intent(this, NotificationActivity::class.java)
            val pi = PendingIntent.getActivity(this, 0, intent, 0)

            // 传入与创建渠道时，相同的id
            val notification = NotificationCompat.Builder( this, "important")
                    .setContentIntent(pi)
                    .setContentTitle("title")
                    .setContentText("Text")
                    .setSmallIcon(R.drawable.small_icon)
                    .setLargeIcon( BitmapFactory.decodeResource(resources, R.drawable.large_icon) )
                    .setAutoCancel(true)
                    .build()

            manager.notify(1, notification)
        }


    }
}