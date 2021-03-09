package com.jerryweather.android.logic

import androidx.lifecycle.liveData
import com.jerryweather.android.logic.dao.PlaceDao
import com.jerryweather.android.logic.model.Place
import com.jerryweather.android.logic.model.Weather
import com.jerryweather.android.logic.network.JerryWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

/**
 *作者：张晋瑞
 *日期：2021/3/4
 *说明：数据仓库
 */
object Repository {
    fun searchPlaces(query:String)=fire(Dispatchers.IO){
        val placeResponse = JerryWeatherNetwork.searchPlaces(query)
        if (placeResponse.status == "ok") {
            val places = placeResponse.places//提取place节点的列表数据
            Result.success(places)
        } else {
            Result.failure(RuntimeException("response status is ${placeResponse.status}"))
        }
    }

    fun refreshWeather(lng:String,lat:String)= fire(Dispatchers.IO){
        coroutineScope {
            val deferredRealtime=async { JerryWeatherNetwork.getRealtimeWeather(lng,lat) }
            val deferredDaily=async { JerryWeatherNetwork.getDailyWeather(lng,lat) }
            val realtimeResponse=deferredRealtime.await()
            val dailyResponse=deferredDaily.await()
            if (realtimeResponse.status=="ok"&&dailyResponse.status=="ok"){
                val weather=Weather(realtimeResponse.result.realtime,dailyResponse.result.daily)
                Result.success(weather)
            }else{
                Result.failure(RuntimeException("realtime response status is ${realtimeResponse.status}+daily response status is ${dailyResponse.status}"))
            }
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
    fun savePlace(place: Place)=PlaceDao.savePlace(place)
    fun getSavedPlace()=PlaceDao.getSavedPlace()
    fun isPlaceSaved()=PlaceDao.isPlaceSaved()
}