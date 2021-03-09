package com.jerryweather.android.logic.dao

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.jerryweather.android.JerryWeatherApplication
import com.jerryweather.android.logic.model.Place

/**
 *作者：张晋瑞
 *日期：2021/3/9
 *说明：记录选中的城市
 */
object PlaceDao {
    private fun sharePreference()=JerryWeatherApplication.context.getSharedPreferences("jerry_weather",Context.MODE_PRIVATE)
    fun savePlace(place:Place){
        sharePreference().edit{
            putString("place",Gson().toJson(place))
        }
    }
    fun getSavedPlace():Place{
        val placeJson= sharePreference().getString("place","")
        return Gson().fromJson(placeJson,Place::class.java)
    }
    fun isPlaceSaved()= sharePreference().contains("place")
}