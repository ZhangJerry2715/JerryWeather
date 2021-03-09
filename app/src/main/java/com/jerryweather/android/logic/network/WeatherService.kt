package com.jerryweather.android.logic.network

import com.jerryweather.android.JerryWeatherApplication
import com.jerryweather.android.logic.model.DailyResponse
import com.jerryweather.android.logic.model.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 *作者：张晋瑞
 *日期：2021/3/5
 *说明：访问天气信息API的Retrofit接口
 */
interface WeatherService {
    @GET("v2.5/${JerryWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(@Path("lng")lng:String,@Path("lat")lat:String):Call<RealtimeResponse>
    @GET("v2.5/${JerryWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(@Path("lng") lng: String,@Path("lat") lat: String):Call<DailyResponse>
}