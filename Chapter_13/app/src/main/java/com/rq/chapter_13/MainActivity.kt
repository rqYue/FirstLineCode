package com.rq.chapter_13

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings.Global.putInt
import androidx.core.content.edit
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel
    lateinit var sp: SharedPreferences

    val sharedPrefKey = "count_reserved"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycle.addObserver(MyObserver(lifecycle))

        sp = getPreferences(Context.MODE_PRIVATE)
        val countReserved = sp.getInt(sharedPrefKey, 0)

        viewModel = ViewModelProvider(this, MainViewModelFactory(countReserved)).get(MainViewModel::class.java)

        button.setOnClickListener {
            viewModel.plus()
    //        refreshCounter()
        }

        clear.setOnClickListener {
            viewModel.clear()
        //    refreshCounter()
        }

        // 进行数据的监听，并对应进行刷新
        viewModel.counter.observe(this) { count ->
            textView.text = count.toString()
        }
    }

    // 使用LiveData 取代 refresh 操作
//    private fun refreshCounter() {
//        textView.text = viewModel.counter.toString()
//    }

    override fun onPause() {
        super.onPause()
        // 对计数进行保存
        sp.edit {
            putInt(sharedPrefKey, viewModel.counter.value ?:0)
        }

    }

//    class MyObserver {
//
//        fun activityStart() {
//
//        }
//
//        fun activityStop() {
//
//        }
//    }
//
//    class MainActivity : AppCompatActivity() {
//        lateinit var observer: MyObserver
//
//        override fun onCreate(savedInstanceState: Bundle?) {
//            super.onCreate(savedInstanceState)
//            observer = MyObserver()
//        }
//
//        override fun onStart() {
//            super.onStart()
//            observer.activityStart()
//        }
//
//        override fun onStop() {
//            super.onStop()
//            observer.activityStop()
//        }
//    }

}

