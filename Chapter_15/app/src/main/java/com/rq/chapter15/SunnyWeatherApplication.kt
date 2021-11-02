package com.rq.chapter15

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class SunnyWeatherApplication: Application() {

    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context

        // 申请的令牌值
        const val Token = ""
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}