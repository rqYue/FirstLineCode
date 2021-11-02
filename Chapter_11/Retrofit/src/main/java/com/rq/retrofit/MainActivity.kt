package com.rq.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            val retrofit = Retrofit.Builder()
                .baseUrl("http://laocal/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val appService = retrofit.create(AppService::class.java)
            appService.getAppData().enqueue(object : Callback<List<App>> {

                override fun onResponse(call: Call<List<App>>, response: Response<List<App>>) {
                    val list = response.body()
                    if(list != null) {
                        for(app in list) {
                            Log.d("MainActivity", "id is ${app.id}")
                            Log.d("MainActivity", "name is ${app.name}")
                            Log.d("MainActivity", "version is ${app.version}")
                        }
                    }
                }

                override fun onFailure(call: Call<List<App>>, t: Throwable) {
                    t.printStackTrace()
                }
            })
        }

//        val appService = ServiceCreator.create(AppService::class.java)

        val appService = ServiceCreator.create<AppService>()
    }

    // 将挂起函数 await() 函数定义为 Call<T> 的扩展函数
    // 所有返回值为 Call 类型的 Retrofit 网络请求接口都可以直接调用 await() 函数
    suspend fun <T> Call<T>.await(): T {
        // 使用 suspendCoroutine 挂起当前协程
        return suspendCoroutine { continuation ->
            // 由于扩展函数，拥有 Call 对象的上下文，直接调用 enqueue 方法进行网络请求
            enqueue(object : Callback<T>{
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) {
                        continuation.resume(body)
                    }
                    else {
                        // 返回值为 null 时，抛出异常或者做其他处理
                        continuation.resumeWithException(
                                RuntimeException("response body is null")
                        )
                    }
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })

        }
    }

    suspend fun getAppData() {
        // 异常处理实际上可以抛出，同一由上层类进行处理
        try {
            // 调用 await 函数进行请求
            val appList = ServiceCreator.create<AppService>().getAppData().await()
            // 对服务器响应的数据进行处理
        } catch (e: Exception) {
            // 对异常情况进行处理
        }
    }

}