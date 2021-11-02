package com.rq.context

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class MyApplication : Application() {

    companion object {
        // 设置为静态变量容易导致内存泄漏，Application 的context 不存在该问题，因此添加注解不检查
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}