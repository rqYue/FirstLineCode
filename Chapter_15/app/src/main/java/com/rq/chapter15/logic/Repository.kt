package com.rq.chapter15.logic

import androidx.lifecycle.liveData
import com.rq.chapter15.logic.model.Place
import com.rq.chapter15.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers

object Repository {

    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            }
            else {

            }
        } catch (e : Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}