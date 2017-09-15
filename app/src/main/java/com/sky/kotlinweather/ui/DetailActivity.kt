package com.sky.kotlinweather.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.widget.TextView

import com.sky.kotlinweather.R
import com.sky.kotlinweather.R.id.*
import com.sky.kotlinweather.data.WeatherIcon
import com.sky.kotlinweather.data.db.CityInfoTable.CITY_NAME
import com.sky.kotlinweather.data.db.DayWeatherTable.CITY_ID
import com.sky.kotlinweather.domain.DayWeather
import com.sky.kotlinweather.domain.GetWeatherDetailCommand
import com.sky.kotlinweather.extensions.color
import com.sky.kotlinweather.extensions.textColor
import com.sky.kotlinweather.ui.interfaces.ToolbarManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.city_weather_item.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread

class DetailActivity : AppCompatActivity(),ToolbarManager{
    override val toolbar: Toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    companion object {
        val CITY_ID = "cityId"
        val CITY_NAME = "cityName"
        val DATE = "date"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        val city = intent.getStringExtra(CITY_NAME)
        val date = intent.getStringExtra(DATE)
        val id = intent.getStringExtra(CITY_ID)
        initToolbar()
        toolbarTitle = city
        enableHomeAsUp { onBackPressed() }
        requestDetail(id, date)
    }


    private fun requestDetail(cityId: String, date: String) {
        doAsync {
            val dayWeather = GetWeatherDetailCommand(cityId, date).execute()
            uiThread {
                bindToView(dayWeather)
            }
        }
    }

    private fun bindToView(dayWeather: DayWeather) = with(dayWeather) {
        bindDesAndIcon(desDaytime, desNight)
        bindTmp(maxTmp to maxTemperature, minTmp to minTemperature)
    }

    private fun bindDesAndIcon(desDaytime: String, desNight: String) {
        daytimeWeather.text = desDaytime
        nightWeather.text = desNight

        val daytimeIconUrl = WeatherIcon.ICON_URL[desDaytime]
        val nightIconUrl = WeatherIcon.ICON_URL[desNight]
        if (daytimeIconUrl != null) {
            Picasso.with(ctx).load(daytimeIconUrl).into(daytimeIcon)
        }
        if (nightIconUrl != null) {
            Picasso.with(ctx).load(nightIconUrl).into(nightIcon)
        }
    }

    private fun bindTmp(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}º"
        it.second.textColor = color(
                when (it.first) {
                    in -500..0 -> android.R.color.holo_red_dark
                    in 0..15 -> android.R.color.black
                    in 15..28 -> android.R.color.holo_green_light
                    in 28..32 -> android.R.color.holo_blue_dark
                    in 32..38 -> android.R.color.holo_orange_light
                    else -> android.R.color.holo_red_light
                })
    }
}
