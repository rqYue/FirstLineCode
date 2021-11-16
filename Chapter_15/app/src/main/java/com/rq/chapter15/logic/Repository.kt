package com.rq.chapter15.logic

import androidx.lifecycle.liveData
import com.rq.chapter15.logic.model.Place
import com.rq.chapter15.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.RuntimeException

object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            }
            else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e : Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}