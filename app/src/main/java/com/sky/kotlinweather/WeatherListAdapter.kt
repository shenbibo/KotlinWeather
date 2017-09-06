package com.sky.kotlinweather

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.widget.TextView
import com.sky.kotlinweather.domain.CityInfo

/**
 * 天气适配器类
 * 详述类的功能。
 * Created by sky on 2017/9/6.
 */

class WeatherListAdapter(private val items: List<CityInfo>) : RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
            = ViewHolder(TextView(parent.context))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(items[position]){
            holder.textView.text = "name = $name, country = $country, location = $location"
        }
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)
}