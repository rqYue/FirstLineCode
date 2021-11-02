package com.rq.servicetest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    private val TAG = "MyService"

    private val mBinder = DownloadBinder()

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate executed")
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.e(TAG, "onStartCommand executed")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(TAG, "onDestroy executed")
    }

    override fun onBind(intent: Intent) : IBinder {
        return mBinder
    }

    class DownloadBinder : Binder() {

        fun startDownload() {
            Log.e("", "startDownload executed")
        }

        fun getProgress() : Int {
            Log.e("", "getProgress executed")
            return 0
        }
    }

}