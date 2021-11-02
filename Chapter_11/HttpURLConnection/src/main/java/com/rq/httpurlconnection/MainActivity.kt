package com.rq.httpurlconnection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.CacheResponse
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sendRequestBtn.setOnClickListener {
            sendRequestWithHttpConnection()
        }
    }

    private fun sendRequestWithHttpConnection() {
        // 开启线程发送网络请求
        thread {
            var connection: HttpURLConnection? = null
            try {
                val response = StringBuilder()

                val url = URL("https://www.baidu.com")
                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 8000
                connection.readTimeout = 8000

                // 设置请求方法为 POST ，将数据以键值对的方式提交，数据与数据间使用&隔开
                connection.requestMethod = "POST"
                val output = DataOutputStream(connection.outputStream)
                output.writeBytes("username=admin&password=123456")

                // 获取输入流
                val input = connection.inputStream

                // 对获取到的输入流进行读取
                val reader = BufferedReader(InputStreamReader(input))
                reader.use {
                    reader.forEachLine {
                        response.append(it)
                    }
                }
                showResponse(response.toString())
            } catch (e : Exception){
                e.printStackTrace()
            } finally {
                connection?.disconnect()
            }
        }
    }

    private fun showResponse(response: String) {
        runOnUiThread {
            responseText.text = response
        }
    }
}