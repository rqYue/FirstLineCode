package com.rq.retrofit

import okhttp3.Response
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

// Retrofit 接口文件以具体的功能种类名开头，并且以 Service 结尾
interface AppService {

    // 使用@GET注解，表示发起一条GET请求，请求地址为@GET注解中的具体参数，此处传入相对路径
    @GET("get_data.json")
    // 方法返回值必须声明为 Retrofit 中内置的Call类型，并且通过泛型指定响应的数据应转换的对象
    // Call Adapter 功能允许自定义方法返回值的类型，Retrofit 结合 RxJava 使用可以将返回值声明成 Observable、Flowable 等类型
    fun getAppData(): Call<List<App>>
}
