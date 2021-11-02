package com.rq.sharedpreferences

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.edit
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private final val TAG = "MAIN_ACTIVITY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        saveButton.setOnClickListener {
            val editor = getSharedPreferences("data", MODE_PRIVATE).edit()
            editor.putString("name", "tom")
            editor.putInt("age", 28)
            editor.putBoolean("married", false)
            editor.apply()

//            getSharedPreferences("data", Context.MODE_PRIVATE).open {
//                editor.putString("name", "tom")
//                editor.putInt("age", 28)
//                editor.putBoolean("married", false)
//            }
        }

        restoreButton.setOnClickListener {
            val prefs = getSharedPreferences("data", MODE_PRIVATE)
            val name = prefs.getString("name", "")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)

            Log.d(TAG, "name is $name")
            Log.d(TAG, "age is $age")
            Log.d(TAG, "married is $married")

        }
    }

    fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {
        val editor = edit()
        editor.block()
        // 最后自动提交数据
        editor.apply()
    }
}