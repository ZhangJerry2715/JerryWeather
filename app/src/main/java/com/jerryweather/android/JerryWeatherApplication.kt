package com.jerryweather.android

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 *作者：张晋瑞
 *日期：2021/3/4
 *说明：全局application
 */
class JerryWeatherApplication : Application() {
    companion object{
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
        const val TOKEN="oEHCIjp13ZmTGA4w"//彩云天气的令牌值
    }

    override fun onCreate() {
        super.onCreate()
        context=applicationContext
    }
}