package com.rq.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main()
    }

    fun main() {
        runBlocking {
            val start = System.currentTimeMillis()
            val  deferred1 = async {
                delay(1000)
                5 + 5
            }

            val deferred2 = async {
                delay(1000)
                4 + 6
            }

            println("result is ${deferred1.await() + deferred2.await()}.")
            val end = System.currentTimeMillis()
            println("cost ${end - start} ms.")

        }
    }

}