package com.jerryweather.android.logic.model

/**
 *作者：张晋瑞
 *日期：2021/3/5
 *说明：用于封装实时天气和未来几天的节点数据
 */
data class Weather (val realtime:RealtimeResponse.Realtime,val daily: DailyResponse.Daily)