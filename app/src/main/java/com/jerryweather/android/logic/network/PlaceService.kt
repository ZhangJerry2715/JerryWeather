package com.jerryweather.android.logic.network

import com.jerryweather.android.JerryWeatherApplication
import com.jerryweather.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 *作者：张晋瑞
 *日期：2021/3/4
 *说明：访问彩云天气城市搜索Retrofit接口
 */
interface PlaceService{
    @GET("v2/place?token=${JerryWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query")query: String):Call<PlaceResponse>
}