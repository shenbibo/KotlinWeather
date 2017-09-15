package com.sky.kotlinweather.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.sky.kotlinweather.R
import com.sky.kotlinweather.base.App
import com.sky.kotlinweather.domain.CityWeatherList
import com.sky.kotlinweather.domain.GetCityWeatherCommand
import com.sky.kotlinweather.extensions.DelegatesExt
import com.sky.kotlinweather.ui.interfaces.ToolbarManager
import com.sky.slog.LogcatTree
import com.sky.slog.Slog
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(), ToolbarManager {

    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    private val cityName: String by DelegatesExt.preference(App.instance,
                                                            SettingActivity.CITY_NAME,
                                                            SettingActivity.DEFAULT_CITY)

    override fun onCreate(savedInstanceState: Bundle?) {
        Slog.init(LogcatTree()).simpleMode(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initListener()
    }

    override fun onResume() {
        super.onResume()
        requestCityWeather(cityName)
    }

    private fun initViews() {
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

    // 使用协程这一实验性质的功能
    @Suppress("EXPERIMENTAL_FEATURE_WARNING")
    private fun requestCityWeather(name: String) = async(UI) {
        val result = bg { GetCityWeatherCommand(name).execute() }
        updateUI(result.await())
    }

    private fun updateUI(weatherList: CityWeatherList) {
        cityWeatherList.adapter = WeatherListAdapter(weatherList.dayWeather) { _, (date) ->
            startActivity<DetailActivity>(DetailActivity.CITY_ID to weatherList.id,
                                          DetailActivity.CITY_NAME to weatherList.cityName,
                                          DetailActivity.DATE to date)

        }
        cityWeatherList.adapter.notifyDataSetChanged()

        toolbarTitle = "${weatherList.cityName} (${weatherList.country})"
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
