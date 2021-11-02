package com.rq.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.work.*
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doworkBtn.setOnClickListener {

            // 构建单次运行的后台请求任务
            val request1 = OneTimeWorkRequest.Builder(SimpleWorker::class.java).build()

            // 构建周期性的后台任务请求
            // 静态变量定义 MIN_PERIODIC_INTERVAL_MILLIS， 时间最短为 15分钟
            val request2 = PeriodicWorkRequest.Builder(SimpleWorker::class.java, 15, TimeUnit.MINUTES).build()

            val request3 = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                // 延迟 5分钟执行
                .setInitialDelay(5, TimeUnit.MINUTES)
                // 添加 tag
                .addTag("simple")
                .build()

            // 执行任务
            WorkManager.getInstance(this).enqueue(request1)

            // 通过 tag 取消后台任务，可同时取消多个相同 tag的任务
            WorkManager.getInstance(this).cancelAllWorkByTag("simple")

            // 通过id 取消后台任务
            WorkManager.getInstance(this).cancelWorkById(request3.id)

            // 一次性取消所有的后台任务请求
            WorkManager.getInstance(this).cancelAllWork()


            // 设置 retry重试，重新执行任务
            val request4 = OneTimeWorkRequest.Builder(SimpleWorker::class.java)
                .setBackoffCriteria(BackoffPolicy.LINEAR, 10, TimeUnit.SECONDS)
                .build()

            // 对后台执行的结果进行监听
            WorkManager.getInstance(this)
                .getWorkInfoByIdLiveData(request4.id)
                .observe(this) { workInfo ->
                    if (workInfo.state == WorkInfo.State.SUCCEEDED) {
                        Log.d("MainActivity", "do work succeeded")
                    }
                    else if (workInfo.state == WorkInfo.State.FAILED) {
                        Log.d("MainActivity", "do work failed")
                    }
                }

            // 链式执行任务，只能链式执行单次任务，任务中不能加入周期性任务
            WorkManager.getInstance(this)
                .beginWith(request1)
//                .then(request2)
                .then(request3)
                .then(request4)
                .enqueue()

        }
    }
}