package com.rq.chapter15.logic.network

import com.rq.chapter15.SunnyWeatherApplication
import com.rq.chapter15.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?token=${SunnyWeatherApplication.Token}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String) : Call<PlaceResponse>

//    https://api.caiyunapp.com/v2/place?query=北京&token={token}&lang=zh_CN
//
//    {"status":"ok","query":"北京",
//        "places":[
//            {"name":"北京市","location":{"lat":39.9041999,"lng":116.4073963}, "formatted_address":"中国北京市"},
//            {"name":"北京西站","location":{"lat":39.89491,"lng":116.322056}, "formatted_address":"中国 北京市 丰台区 莲花池东路118号"},
//            {"name":"北京南站","location":{"lat":39.865195,"lng":116.378545}, "formatted_address":"中国 北京市 丰台区 永外大街车站路12号"},
//            {"name":"北京站(地铁站)","location":{"lat":39.904983,"lng":116.427287}, "formatted_address":"中国 北京市 东城区 2号线"}
//    ]}
}