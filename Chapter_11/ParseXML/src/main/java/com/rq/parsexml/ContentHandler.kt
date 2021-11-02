package com.rq.parsexml

import android.util.Log
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import java.lang.StringBuilder

class ContentHandler : DefaultHandler() {

    private var nodeName = ""

    private lateinit var id : StringBuilder
    private lateinit var name : StringBuilder
    private lateinit var version : StringBuilder

    // 开始XML 解析时候调用
    override fun startDocument() {
        id = StringBuilder()
        name = StringBuilder()
        version = StringBuilder()
    }

    //开始在解析某个节点时候调用
    override fun startElement(uri: String?, localName: String?, qName: String?, attributes: Attributes?) {
        // 记录当前节点名称
        nodeName = localName.toString()
        Log.d("ContentHandler", "uri is $uri")
        Log.d("ContentHandler", "localName is $localName")
        Log.d("ContentHandler", "qName is $qName")
        Log.d("ContentHandler", "attributes is $attributes")
    }

    // 开始获取节点内容时候调用
    override fun characters(ch: CharArray?, start: Int, length: Int) {
        // 根据当前节点名判断将内容添加到哪一个 StringBuilder 对象中
        when (nodeName) {
            "id" -> id.append(ch, start, length)
            "name" -> name.append(ch, start, length)
            "version" -> version.append(ch, start, length)
        }

    }

    // 结束解析某个节点时候调用
    override fun endElement(uri: String?, localName: String?, qName: String?) {
        if ("app" == localName) {
            Log.d("ContentHandler", "id is ${id.toString().trim()}")
            Log.d("ContentHandler", "name is ${name.toString().trim()}")
            Log.d("ContentHandler", "version is ${version.toString().trim()}")

            // 最后将 StringBuilder清空，防止解析多个节点内容出错
            id.setLength(0)
            name.setLength(0)
            version.setLength(0)
        }
    }

    // 结束解析XNL 时调用
    override fun endDocument() {
    }
}