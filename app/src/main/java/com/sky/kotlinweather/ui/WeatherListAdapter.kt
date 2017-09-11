package com.sky.kotlinweather.ui

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sky.kotlinweather.R
import com.sky.kotlinweather.data.WeatherIcon
import com.sky.kotlinweather.domain.DayWeather
import com.sky.kotlinweather.extensions.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.city_weather_item.view.*

/**
 * 天气适配器类
 * 详述类的功能。
 * Created by sky on 2017/9/6.
 */

class WeatherListAdapter(private val items: List<DayWeather>, private val itemClick: (View) -> Unit)
    : RecyclerView.Adapter<WeatherListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx).inflate(R.layout.city_weather_item, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDataToView(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(view: View, private val itemClick: (View) -> Unit) : RecyclerView.ViewHolder(view) {

        fun bindDataToView(cityWeather: DayWeather) {
            with(cityWeather) {
                itemView.date.text = date
                itemView.daytimeWeather.text = desDaytime
                itemView.nightWeather.text = desNight
                itemView.maxTemperature.text = "$maxTmpº"
                itemView.minTemperature.text = "$minTmpº"
                itemView.setOnClickListener { itemClick(it) }
                val daytimeIconUrl = WeatherIcon.ICON_URL[desDaytime]
                val nightIconUrl = WeatherIcon.ICON_URL[desNight]
                if (daytimeIconUrl != null) {
                    Picasso.with(itemView.ctx).load(daytimeIconUrl).into(itemView.daytimeIcon)
                }

                if (nightIconUrl != null) {
                    Picasso.with(itemView.ctx).load(nightIconUrl).into(itemView.nightIcon)
                }
            }
        }
    }
}