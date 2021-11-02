package com.rq.handlertest

import android.os.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    val updateText = 1

    val handler = object : Handler(Looper.getMainLooper()) {

        override fun handleMessage(msg: Message) {
            when (msg.what) {
                updateText -> textView.text = "bbb"
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeTextBtn.setOnClickListener {

            thread {
                val msg = Message()
                msg.what = updateText
                handler.sendMessage(msg)
            }
        }

//        MyThread().start()

//        val myThread = MyThread()
//        Thread(myThread).start()

//        Thread {
//            // 编写具体逻辑
//        }.start()

        thread {
            // 编写具体逻辑
        }
    }

//    class DownloadTask : AsyncTask<Unit, Int, Boolean>() {
//
//        override fun onPreExecute() {
//            progressDialog.show()
//        }
//
//        override fun doInBackground(vararg params: Unit?) = try {
//
//            while (true) {
//                val downloadPercent = doDownload()
//                publishProgress(downloadPercent)
//                if(downloadPercent >= 100) {
//                    break;
//                }
//            }
//            true
//        } catch (e : Exception) {
//            false
//        }
//
//        override fun onProgressUpdate(vararg values: Int?) {
//            progressDialog.setMessage("Downloaded ${values[0]}%")
//        }
//
//        override fun onPostExecute(result: Boolean?) {
//            progressDialog.dismiss()
//
//            if(result) {
//                Toast.makeText(context, "Download succeeded", Toast.LENGTH_LONG).show()
//            } else {
//                Toast.makeText(context, "Download failed", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
//
//    DownloadTask().execute()


//    class MyThread : Thread() {
//        override fun run() {
//            // 编写耗时逻辑
//        }
//    }
//
//    class MyThread : Runnable {
//        override fun run() {
//            //编写耗时逻辑
//        }
//    }

}