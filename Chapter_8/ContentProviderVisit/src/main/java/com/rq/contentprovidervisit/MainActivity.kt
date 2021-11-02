package com.rq.contentprovidervisit

import android.content.UriMatcher
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.contentValuesOf
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.reflect.KProperty

class MainActivity : AppCompatActivity() {

    var bookId : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addData.setOnClickListener {
            val uri  = Uri.parse("content://com.rq.provider/book")
            val values = contentValuesOf("name" to "abc", "author" to "def", "pages" to 1000, "price" to "22")

            val newUri = contentResolver.insert(uri, values)

            bookId = newUri?.pathSegments?.get(1)
        }

        queryData.setOnClickListener {
            val uri = Uri.parse("content://com.rq.provider/book")
            contentResolver.query(uri, null, null, null,null)?.apply {

                while (moveToNext()) {
                    val name = getString( getColumnIndex("name"))
                    val author = getString( getColumnIndex("author"))
                    val pages = getString( getColumnIndex("pages"))
                    val price = getString( getColumnIndex("price"))

                    Log.d("MainActivity", "book name is $name")
                    Log.d("MainActivity", "book author is $author")
                    Log.d("MainActivity", "book pages is $pages")
                    Log.d("MainActivity", "book price is $price")
                }
                close()
            }
        }

        updataData.setOnClickListener {
            bookId?.let {
                val uri = Uri.parse("content://com.rq.provider/book/$it")
                val values = contentValuesOf("name" to "123", "author" to "456", "pages" to 500, "price" to 33)
                contentResolver.update(uri, values, null, null)
            }
        }

        deleteData.setOnClickListener {
            bookId?.let {
                val uri = Uri.parse("content://com.rq.provider/book/$it")
                contentResolver.delete(uri, null, null)
            }
        }

    }

    class MySet<T> (val helperSet: HashSet<T>) : Set<T> by helperSet{
        // 加入新方法
        fun a() = println("aaq")
        // 重写方法
        override fun isEmpty() = true
    }

    class MyClass {

        var p by Delegate()
    }

    // 类中需要实现 getValue() 和 setValue 两个方法，并且都要使用 operator 关键字进行声明
    class Delegate {

        var propValue: Any? = null

        // 第一个参数用于声明该 Delegate 类的委托功能可以在什么类中使用
        // 表示仅在 MyClass 类中可以使用
        operator fun getValue(myClass: MyClass, prop: KProperty<*> ): Any? {
            return propValue
        }

        operator fun setValue(myClass: MyClass, prop: KProperty<*>, value: Any?) {
            propValue = value
        }
    }

    // 用于创建 Later 类的实例，并将接收的函数类型参数传递给 Later 类的构造函数
    fun <T> later(blcok: () -> T) = Later(blcok)

    // 定义泛型类
    class Later<T>(val block: () -> T ) {
        var value: Any? = null

        // 懒加载不需要对属性赋值，只需要实现 getValue 方法
        operator fun getValue(any: Any?, prop: KProperty<*>) : T {

            if(value == null){
                value = block()
            }
            return value as T

        }
    }

    // 进行调用
//    private val uriMatcher by later {
//        val matcher = UriMatcher(UriMatcher.NO_MATCH)
//        matcher.addURI(authority, "book", bookDir)
//        matcher
//    }

}