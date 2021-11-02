package com.rq.networkrequestcallback

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

// 将方法提取到公共类中
object HttpUtil {

    // 使用 HttpURLConnection 实现，子线程中处理请求
    fun sendHttpRequest(address : String, listener: HttpCallbackListener) {

        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()
                val url = URL(address)
                connection = url.openConnection() as HttpURLConnection
                connection.connectTimeout = 8000
                connection.readTimeout = 8000
                val input = connection.inputStream
                val reader = BufferedReader(InputStreamReader(input))

                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                // 回调 onFinish() 方法
                listener.onFinish(response.toString())

            } catch (e: Exception) {
                e.printStackTrace()
                // 回调 onError() 方法
                listener.onError(e)
            } finally {
                connection?.disconnect()
            }
        }
    }

    // 使用OkHttp 实现
    fun sendOkHttpRequest(address: String, callback: okhttp3.Callback) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(address)
            .build()
        // OkHttp 在 enqueue 内部开启子线程
        client.newCall(request).enqueue(callback)
    }

}