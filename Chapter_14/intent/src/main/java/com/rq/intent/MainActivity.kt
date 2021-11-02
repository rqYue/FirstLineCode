package com.rq.intent

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //1、通常设置方法
        val intent1 = Intent(this, SecondActivity::class.java)
        intent1.putExtra("string_data", "hello")
        intent1.putExtra("int_data",100)
        startActivity(intent1)

        //2、使用 Serializable 接口
        val person = Person()
        person.name = "Tom"
        person.age = 20
        val intent2 = Intent(this, SecondActivity::class.java)
        intent2.putExtra("person_data", person)
        startActivity(intent2)

        //3、使用 Parcelable 接口
        val people = People()
        people.name = "Tom"
        people.age = 20
        val intent3 = Intent(this, SecondActivity::class.java)
        intent3.putExtra("people_data", people)
        startActivity(intent3)
    }
}