package com.sky.kotlinweather.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.sky.kotlinweather.R
import com.sky.kotlinweather.domain.GetCityWeatherCommand
import com.sky.slog.LogcatTree
import com.sky.slog.Slog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.city_weather_item.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        Slog.init(LogcatTree()).simpleMode(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        cityWeatherList.layoutManager = LinearLayoutManager(this)

        initListener()
    }

    private fun initListener() {
        searchButton.setOnClickListener{
            requestCityWeather(searchTextView.text.toString())
        }
    }

    private fun requestCityWeather(name : String) {
        doAsync {
            val weatherList = GetCityWeatherCommand(name).execute()
            uiThread {
                cityWeatherList.adapter = WeatherListAdapter(weatherList.dayWeather) {
                    toast(it.date.text.toString())
                }
                cityWeatherList.adapter.notifyDataSetChanged()
            }
        }
    }

//    private fun requestCityInfo(){
//        val cityNameList = listOf("北京", "上海", "深圳")
//        doAsync{
//            val cityResult = RequestCityCommand().execute(cityNameList)
//            uiThread {
//                with(cityWeatherList){
//                    adapter = WeatherListAdapter(cityResult)
//                    adapter.notifyDataSetChanged()
//                }
//            }
//        }
//    }
}
