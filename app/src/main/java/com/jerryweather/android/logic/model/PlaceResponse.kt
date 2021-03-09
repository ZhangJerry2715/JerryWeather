package com.jerryweather.android.logic.model

import com.google.gson.annotations.SerializedName

/**
 *作者：张晋瑞
 *日期：2021/3/4
 *说明：根据位置信息接口返回的json数据定义相应的数据模型
 */
data class PlaceResponse(val status:String,val places:List<Place>)

data class Place (val name:String, val location: Location, @SerializedName("formatted_address")val address:String)

data class Location(val lng:String,val lat:String)