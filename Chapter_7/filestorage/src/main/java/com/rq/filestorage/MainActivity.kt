package com.rq.filestorage

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputText = load()
        if(inputText.isNotEmpty()){
            editText.setText(inputText)
            // 将输入光标移动到文本的末尾，方便继续输入
            editText.setSelection(inputText.length)
            Toast.makeText(this, "Restoring Succeeded", Toast.LENGTH_LONG).show()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        val inputText = editText.text.toString()
        save(inputText)
    }

    private fun save(inputText : String){
        try {
            val output = openFileOutput("data", Context.MODE_PRIVATE)
            val writer = BufferedWriter( OutputStreamWriter(output) )
            writer.use {
                it.write(inputText)
            }
        } catch (e : Exception) {
            e.printStackTrace()
        }
    }

    private fun load(): String {
        val content = StringBuilder()

        try {
            val input = openFileInput("data")
            val reader = BufferedReader(InputStreamReader(input))
            reader.use {
                reader.forEachLine {
                    content.append(it)
                }
            }
        } catch (e : Exception){
            e.printStackTrace()
        }
        return content.toString()
    }

}