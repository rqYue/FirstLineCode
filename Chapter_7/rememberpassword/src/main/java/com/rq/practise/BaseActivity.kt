package com.rq.practise

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

//创建Activity 的基类
open class BaseActivity : AppCompatActivity(){

    lateinit var receiver: ForceOfflineReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.rq.practise.FORCE_OFFLINE")
        receiver = ForceOfflineReceiver()
        registerReceiver(receiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }

    inner class ForceOfflineReceiver : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            if (context != null) {
                AlertDialog.Builder(context).apply {
                    setTitle("Warning")
                    setMessage("You are forced to be offline. Please try to login again.")
                    // 设置不可取消
                    setCancelable(false)
                    setPositiveButton("OK") { _, _ ->
                        ActivityCollector.finishAll()
                        val i = Intent(context, LoginActivity::class.java)
                        startActivity(i)
                    }
                    show()
                }
            }
        }

    }
}