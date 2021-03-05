package com.jerryweather.android.logic

import androidx.lifecycle.liveData
import com.jerryweather.android.logic.network.JerryWeatherNetwork
import kotlinx.coroutines.Dispatchers
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

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }
}