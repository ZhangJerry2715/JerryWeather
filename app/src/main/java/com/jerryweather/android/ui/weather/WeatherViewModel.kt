package com.jerryweather.android.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jerryweather.android.logic.Repository
import com.jerryweather.android.logic.model.Location
import retrofit2.Response

/**
 *作者：张晋瑞
 *日期：2021/3/8
 *说明：天气的viewmodel
 */
class WeatherViewModel :ViewModel() {
    private val locationLiveData=MutableLiveData<Location>()
    var locationLng=""
    var locationLat=""
    var placeName=""
    fun refreshWeather(lng:String,lat:String){
        locationLiveData.value=Location(lng,lat)
    }
    val weatherLiveData=Transformations.switchMap(locationLiveData){
        Repository.refreshWeather(it.lng,it.lat)
    }

}