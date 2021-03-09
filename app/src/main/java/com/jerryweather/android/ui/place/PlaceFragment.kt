package com.jerryweather.android.ui.place

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jerryweather.android.MainActivity
import com.jerryweather.android.R
import com.jerryweather.android.ui.weather.WeatherActivity

/**
 *作者：张晋瑞
 *日期：2021/3/5
 *说明：城市搜索之fragment
 */
class PlaceFragment : Fragment() {
    private lateinit var bgImageView: ImageView
    private lateinit var adapter: PlaceAdapter
    val viewModel by lazy { ViewModelProviders.of(this).get(PlaceModel::class.java) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_place, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        bgImageView = activity!!.findViewById(R.id.bgImageView)
        if (activity is MainActivity&&viewModel.isPlaceSaved()){
            val place=viewModel.getSavedPlace()
            val intent= Intent(context, WeatherActivity::class.java).apply {
                putExtra("location_lng",place.location.lng)
                putExtra("location_lat",place.location.lat)
                putExtra("place_name",place.name)
            }
            startActivity(intent)
            activity?.finish()
            return
        }
        val recyclerView: RecyclerView? = activity?.findViewById(R.id.recyclerView)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView?.layoutManager = layoutManager
        adapter = PlaceAdapter(this,viewModel.placeList)
        recyclerView?.adapter = adapter
        val searchPlaceEdit: EditText? = activity?.findViewById(R.id.searchPlaceEdit)
        searchPlaceEdit?.addTextChangedListener { editable ->
            val content = editable.toString()
            if (content.isNotEmpty()) {
                viewModel.searchPlaces(content)
            } else {
                recyclerView?.visibility = View.GONE
                bgImageView.visibility = View.VISIBLE
                viewModel.placeList.clear()
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.placeLiveData.observe(this, Observer {
            val places = it.getOrNull()
            if (places != null) {
                recyclerView?.visibility = View.VISIBLE
                bgImageView.visibility = View.GONE
                viewModel.placeList.clear()
                viewModel.placeList.addAll(places)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "未查询到任何地点", Toast.LENGTH_SHORT).show()
                it.exceptionOrNull()?.printStackTrace()
            }


        })
    }
}