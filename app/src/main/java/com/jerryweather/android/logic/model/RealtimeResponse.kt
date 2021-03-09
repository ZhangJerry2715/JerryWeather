package com.jerryweather.android.logic.model

import com.google.gson.annotations.SerializedName

/**
 *作者：张晋瑞
 *日期：2021/3/5
 *说明：根据实时天气信息接口返回的json数据定义相应的数据模型
 */
data class RealtimeResponse(val status: String, val result: Result) {
    data class Result(val realtime: Realtime)
    data class Realtime(
        val temperature: Float,
        val skycon: String,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)
    data class AQI(val chn: Float)
}