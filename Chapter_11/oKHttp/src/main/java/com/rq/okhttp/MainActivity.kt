package com.rq.okhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.lang.Exception
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendRequestBtn.setOnClickListener {
            sendRequestWithOkHttp()
        }
    }

    private fun sendRequestWithOkHttp() {
        thread {
            try {
                val client = OkHttpClient()
                val request = Request.Builder()
                    .url("https://www.baidu.com")
                    .build()

                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if(responseData != null) {
                    showResponse(responseData)
                }
            } catch (e : Exception) {
                e.printStackTrace()
            }

            // 发送 post请求，提前设置需要发送的参数
            val requestBody = FormBody.Builder()
                .add("username", "admin")
                .add("password", "123456")
                .build()
            val request = Request.Builder()
                .url("https://www.baidu.com")
                .post(requestBody)
                .build()

        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            // 在此进行UI操作，将结果显示到界面上
            responseText.text = response
        }
    }
}