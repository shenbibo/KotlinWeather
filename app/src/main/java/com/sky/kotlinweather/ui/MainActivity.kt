package com.sky.kotlinweather.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.sky.kotlinweather.R
import com.sky.kotlinweather.R.id.*
import com.sky.kotlinweather.domain.GetCityWeatherCommand
import com.sky.kotlinweather.ui.interfaces.ToolbarManager
import com.sky.slog.LogcatTree
import com.sky.slog.Slog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.city_weather_item.*
import kotlinx.android.synthetic.main.city_weather_item.view.*
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    override fun onCreate(savedInstanceState: Bundle?) {
        Slog.init(LogcatTree()).simpleMode(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initListener()
    }

    private fun initViews(){
        initToolbar()
        cityWeatherList.layoutManager = LinearLayoutManager(this)
    }

    private fun initListener() {
        searchButton.setOnClickListener {
            requestCityWeather(searchTextView.text.toString())
        }

        cityWeatherList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    searchContainer.visibility = View.VISIBLE
                } else {
                    searchContainer.visibility = View.GONE
                }
            }
        })

        attachToScroll(cityWeatherList)
    }

    private fun requestCityWeather(name: String) {
        doAsync {
            val weatherList = GetCityWeatherCommand(name).execute()
            uiThread {
                cityWeatherList.adapter = WeatherListAdapter(weatherList.dayWeather) { _, (date) ->
                    startActivity<DetailActivity>(DetailActivity.CITY_ID to weatherList.id,
                                                  DetailActivity.CITY_NAME to weatherList.cityName,
                                                  DetailActivity.DATE to date)

                }
                cityWeatherList.adapter.notifyDataSetChanged()

                toolbarTitle = "${weatherList.cityName} (${weatherList.country})"
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
