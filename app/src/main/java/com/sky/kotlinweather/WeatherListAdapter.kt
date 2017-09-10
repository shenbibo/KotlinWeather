package com.sky.kotlinweather

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sky.kotlinweather.data.DailyWeather
import com.sky.kotlinweather.data.WeatherIcon
import com.sky.kotlinweather.domain.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.city_weather_item.view.*

/**
 * 天气适配器类
 * 详述类的功能。
 * Created by sky on 2017/9/6.
 */

class WeatherListAdapter(private val items: List<DailyWeather>, private val itemClick: (View) -> Unit)
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

        fun bindDataToView(cityWeather: DailyWeather) {
            with(cityWeather) {
                itemView.date.text = date
                itemView.daytimeWeather.text = cond.txt_d
                itemView.nightWeather.text = cond.txt_n
                itemView.maxTemperature.text = "${tmp.max}º"
                itemView.minTemperature.text = "${tmp.min}º"
                itemView.setOnClickListener { itemClick(it) }
                val daytimeIconUrl = WeatherIcon.ICON_URL[cond.txt_d]
                val nightIconUrl = WeatherIcon.ICON_URL[cond.txt_n]
                if(daytimeIconUrl != null){
                    Picasso.with(itemView.ctx).load(daytimeIconUrl).into(itemView.daytimeIcon)
                }

                if(nightIconUrl != null){
                    Picasso.with(itemView.ctx).load(nightIconUrl).into(itemView.nightIcon)
                }
            }
        }
    }
}