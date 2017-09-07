package com.sky.kotlinweather

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sky.kotlinweather.data.DailyWeather
import com.sky.kotlinweather.domain.CityWeatherList
import com.sky.kotlinweather.domain.ctx
import kotlinx.android.synthetic.main.city_weather_item.view.*

/**
 * 天气适配器类
 * 详述类的功能。
 * Created by sky on 2017/9/6.
 */

class WeatherListAdapter(private val items: List<DailyWeather>, val itemClick: (View) -> Unit)
    : RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.city_weather_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDataToView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View, val itemClick: (View) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindDataToView(cityWeather: DailyWeather) {
            with(cityWeather) {
                itemView.date.text = cityWeather.date
                itemView.daytimeWeather.text = cityWeather.cond.txt_d
                itemView.nightWeather.text = cityWeather.cond.txt_n
                itemView.maxTemperature.text = cityWeather.tmp.max.toString()
                itemView.minTemperature.text = cityWeather.tmp.min.toString()
                itemView.setOnClickListener { itemClick(it) }
            }
        }
    }

    interface OnItemClickListener {
        fun onClick(view: View)
    }
}