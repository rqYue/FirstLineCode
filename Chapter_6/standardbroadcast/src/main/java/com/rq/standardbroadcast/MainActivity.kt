package com.rq.standardbroadcast

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            // 设置自定义的广播名称
            val intent = Intent(this.packageName + ".MY_BROADCAST")
            // 默认情况下自定义广播为隐式广播
            // 需要设置包名，指定该广播发送给哪个应用程序，使其成为一条显式广播，否则静态注册的 BroadcastReceiver 将无法接收到
            intent.setPackage(packageName)
            sendBroadcast(intent)
        }

    }
}
