package com.rq.sqlite

import android.content.ContentValues
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //创建数据库，版本为 1
//      val dbHelper = MyDatabaseHelper(this, "BookStore.db", 1)
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

            // 使用SQL 语句
            db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
                    arrayOf("aaa", "bbb", "100", "10"))
            db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
                    arrayOf("111", "222", "200", "30"))
        }

        updateData.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 9.99)

            // 使用 arrayOf 创建数组
            // ？ 为占位符，第四个参数提供一个字符串数组为第三个参数的每个占位符指定相应的内容
            db.update("Book", values, "name = ?", arrayOf("aaa"))

            // 使用SQL 语句
            db.execSQL("update Book set price = ? where name = ?", arrayOf("9.99", "aaa"))
        }

        deleteData.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("100"))

            // 使用SQL 语句
            db.execSQL("delete from Book where pages > ?", arrayOf("100"))
        }

        queryData.setOnClickListener {
            val db = dbHelper.writableDatabase
            // 查询Book 表中的所有的数据
            val cursor = db.query("Book", null, null, null, null, null, null)
            // 指针移动到第一行位置
            if(cursor.moveToFirst()){
                do{
                    // 遍历 Cursor 对象，取出数据并打印
                    // getColumnIndex() 获取某一列在表中对应的位置索引
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getString(cursor.getColumnIndex("pages"))
                    val price = cursor.getString(cursor.getColumnIndex("price"))

                    Log.d(TAG, "book name is $name")
                    Log.d(TAG, "book author is $author")
                    Log.d(TAG, "book pages is $pages")
                    Log.d(TAG, "book price is $price")
                }while (cursor.moveToNext())
            }
            // 关闭游标
            cursor.close()

            // 使用SQL 语句
            val cursor1 = db.rawQuery("select * from Book", null)
        }
    }
}