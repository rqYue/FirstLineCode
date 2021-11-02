package com.rq.networkrequestcallback

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Call
import okhttp3.Response
import java.io.IOException
import java.lang.Exception
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {

            val address = "http://www.baidu.com"

            // 回调接口在子线程中运行，不可进行UI操作
            val response1 = HttpUtil.sendHttpRequest(address, object : HttpCallbackListener {
                override fun onFinish(response: String) {
                    // 得到服务器返回的具体内容
                    Log.e("test", "aaa")
                }

                override fun onError(e: Exception) {
                    // 对异常情况进行处理
                    Log.e("test", "111")
                }
            })

            // 回调接口在子线程中运行，不可进行UI操作
            val response2 = HttpUtil.sendOkHttpRequest(address, object : okhttp3.Callback {
                override fun onResponse(call: Call, response: Response) {
                    // 对异常情况进行处理
                    Log.e("test", "bbb")
                }

                override fun onFailure(call: Call, e: IOException) {
                    // 得到服务器返回的具体内容
                    Log.e("test", "222")
                }

            })
        }
    }
}