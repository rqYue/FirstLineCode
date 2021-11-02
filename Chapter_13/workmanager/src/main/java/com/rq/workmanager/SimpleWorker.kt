package com.rq.workmanager

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters

class SimpleWorker(context: Context, params: WorkerParameters) : Worker(context, params){

    override fun doWork(): Result {
        Log.d("SimpleWork", "do work in SimpleWorker")
        return Result.success()
    }
}