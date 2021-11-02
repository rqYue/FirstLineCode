package com.rq.mycontentprovider

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 更新数据库，版本为2
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        createDatabase.setOnClickListener {
            dbHelper.writableDatabase
        }

        addData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                //对数据进行组装
                put("name", "aaa")
                put("author", "bbb")
                put("pages", 100)
                put("price", 10)
            }
            db.insert("Book", null, values1)

            val values2 = ContentValues().apply {
                //对数据进行组装
                put("name", "111")
                put("author", "222")
                put("pages", 200)
                put("price", 30)
            }
            db.insert("Book", null, values2)
        }
    }
}