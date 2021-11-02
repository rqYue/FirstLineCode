package com.rq.intent

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // 1、通常设置方法
        val name = intent.getStringExtra("string_data")
        val age = intent.getIntExtra("int_data", 0)

        //2、使用 Serializable 接口
        val person = intent.getSerializableExtra("person_data") as Person

        //3、使用 Parcelable 接口
        val people = intent.getParcelableExtra<People>("people_data")

        Log.e("TAG", "name:${people?.name} age:${people?.age}")
    }
}