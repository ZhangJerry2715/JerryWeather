package com.jerryweather.android.logic.network

import retrofit2.await

/**
 *作者：张晋瑞

 *说明：统一网络数据源访问入口
 */
object JerryWeatherNetwork {
    private val placeService=ServiceCreator.create<PlaceService>()

    suspend fun searchPlaces(query:String)= placeService.searchPlaces(query).await()
}