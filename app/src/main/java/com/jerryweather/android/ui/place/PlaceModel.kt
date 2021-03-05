package com.jerryweather.android.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.jerryweather.android.logic.Repository
import com.jerryweather.android.logic.model.Place

/**
 *作者：张晋瑞
 *日期：2021/3/4
 *说明：建立数据逻辑层和UI层的桥梁——viewmodel
 */
class PlaceModel : ViewModel() {
    private val searchLiveData=MutableLiveData<String>()

    fun searchPlaces(query:String){
        searchLiveData.value=query
    }
    val placeLiveData=Transformations.switchMap(searchLiveData){query->
        Repository.searchPlaces(query)//这是真正的从库里获取数据
    }
    val placeList=ArrayList<Place>()//用于缓存数据，横竖屏的时候数据不变
}