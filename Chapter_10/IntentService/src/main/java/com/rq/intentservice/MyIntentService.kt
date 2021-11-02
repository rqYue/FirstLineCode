package com.rq.intentservice

import android.app.IntentService
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class MyIntentService : IntentService("MyIntentService") {

    // 该方法在子线程中运行，用于处理耗时任务
    override fun onHandleIntent(intent: Intent?) {
        Log.d("MyIntentService", "Thread id is ${Thread.currentThread().name}")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MyIntentService", "onDestroy executed")
    }

}