package com.rq.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class MyDatabaseHelper(val context: Context, name: String, version: Int)
    : SQLiteOpenHelper( context, name, null , version){

    private  val createBook = "create table Book (" +
            " id integer primary key autoincrement," +
            "author text," +
            "price real," +
            "pages integer," +
            "name text," +
            "category_id integer)"

    private val createCategory = "create table Category(" +
            "id integer primary key autoincrement," +
            "category_name text," +
            "category_code integer)"

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(createBook)
        db.execSQL(createCategory)
        Toast.makeText(context, "Create succeeded", Toast.LENGTH_LONG).show()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        // 版本1，新建 Category 表格
        if(oldVersion <= 1){
            db?.execSQL(createCategory)
        }

        // 版本2 添加 category_id 项
        if(oldVersion <= 2){
            db?.execSQL("alter table Book add column category_id integer")
        }

//        db?.execSQL("drop table if exists Book")
//        db?.execSQL("drop table if exists Category")
//        // 删除表格之后，重新创建
//        if(db != null) onCreate(db)
    }

}