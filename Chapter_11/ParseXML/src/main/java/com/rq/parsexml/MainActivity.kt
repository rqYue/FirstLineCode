package com.rq.parsexml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient
import okhttp3.Request
import org.xml.sax.InputSource
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory
import java.io.StringReader
import java.lang.Exception
import java.lang.StringBuilder
import javax.xml.parsers.SAXParserFactory
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
                    // 使用本机配置的资源文件
                    .url("http://localhost/get_data.xml")
                    .build()

                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                if(responseData != null) {
                    parseXMLWithPull(responseData)
                    parseXMLWithSAX(responseData)
                }

            } catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun parseXMLWithPull(xmlData: String) {
        try {
            val factory = XmlPullParserFactory.newInstance()
            val xmlPullParser = factory.newPullParser()
            // 将获取的 xml 数据设置
            xmlPullParser.setInput(StringReader(xmlData))
            var eventType = xmlPullParser.eventType

            var id = ""
            var name = ""
            var version = ""

            // 判断解析是否完成
            while (eventType != XmlPullParser.END_DOCUMENT) {

                // 获取当前节点的名称
                val nodeName = xmlPullParser.name
                when(eventType) {
                    // 开始解析某个节点
                    XmlPullParser.START_TAG -> {
                        when(nodeName) {
                            // 使用 nextText() 获取具体内容
                            "id" -> id = xmlPullParser.nextText()
                            "name" -> name = xmlPullParser.nextText()
                            "version" -> version = xmlPullParser.nextText()
                        }
                    }

                    // 完成解析当前节点，进行日志输出
                    XmlPullParser.END_TAG -> {
                        if("app" == nodeName) {
                            Log.d("MainActivity", "id is $id")
                            Log.d("MainActivity", "name is $name")
                            Log.d("MainActivity", "version is $version")
                        }
                    }
                }
                eventType = xmlPullParser.next()
            }
        } catch (e: Exception){
            e.printStackTrace()
        }
    }

    private fun parseXMLWithSAX(xmlData: String) {
        try {
            val factory = SAXParserFactory.newInstance()
            val xmlReader = factory.newSAXParser().xmlReader
            val handler = ContentHandler()

            // 将 ContentHandler 的实例设置到 XMLReader 中
            xmlReader.contentHandler = handler
            // 开始执行解析
            xmlReader.parse(InputSource(StringReader(xmlData)))
        } catch (e : Exception){
            e.printStackTrace()
        }
    }

}