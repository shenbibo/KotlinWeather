package com.sky.kotlinweather.domain

import com.google.gson.Gson
import com.sky.kotlinweather.data.server.BASE_URL_FREE
import com.sky.kotlinweather.data.server.DailyWeather
import com.sky.kotlinweather.data.server.DailyWeatherResponse
import com.sky.kotlinweather.data.server.KEY
import com.sky.slog.Slog
import java.net.URL

/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2017/9/7.
 */

data class CityWeatherList(val id: String,
                           val cityName: String,
                           val country: String,
                           val dayWeather: List<DayWeather>) {
    val size: Int
        get() = dayWeather.size

    operator fun get(position: Int) = dayWeather[position]
}

data class DayWeather(val date: String,
                      val maxTmp: Int,
                      val minTmp: Int,
                      val desDaytime: String,
                      val desNight: String)

class WeatherRequest(private val cityName: String) : Command<DailyWeatherResponse> {
    override fun execute(): DailyWeatherResponse {
        val jsonCityWeather = URL("$BASE_URL_FREE$METHOD?city=$cityName&key=$KEY").readText()
        Slog.json(jsonCityWeather)
        return Gson().fromJson(jsonCityWeather, DailyWeatherResponse::class.java)
    }

    companion object {
        private val METHOD = "forecast"
    }
}

class GetCityWeatherCommand(private val cityName: String) : Command<CityWeatherList> {
    override fun execute(): CityWeatherList {
        val dailyWeatherResponse = WeatherRequest(cityName).execute()
        return covertToCityWeather(dailyWeatherResponse)
    }

    private fun covertToCityWeather(response: DailyWeatherResponse): CityWeatherList =
            with(response.HeWeather5[0]) {
                CityWeatherList(basic.id, basic.city, basic.cnty, covertToDayWeather(daily_forecast))
            }


    private fun covertToDayWeather(dailyForecastList: List<DailyWeather>): List<DayWeather> =
            dailyForecastList.map {
                with(it) {
                    DayWeather(date, tmp.max, tmp.min, cond.txt_d, cond.txt_n)
                }
            }

}