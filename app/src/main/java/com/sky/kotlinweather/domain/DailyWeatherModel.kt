package com.sky.kotlinweather.domain

import com.google.gson.Gson
import com.sky.kotlinweather.data.*
import com.sky.slog.Slog
import java.net.URL

/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2017/9/7.
 */

data class CityWeatherList(val cityName: String,
                           val dailyWeather: List<DailyWeather>)

class WeatherRequest(private val cityName: String) : Command<DailyWeatherResponse> {
    override fun execute(): DailyWeatherResponse {
        val jsonCityWeather = URL("$BASE_URL_FREE$METHOD?city=$cityName&key=$KEY").readText()
        Slog.json(jsonCityWeather)
        return Gson().fromJson(jsonCityWeather, DailyWeatherResponse::class.java)
    }

    companion object {
        val METHOD = "forecast"
    }
}

class GetCityWeatherCommand(private val cityName: String) : Command<CityWeatherList> {
    override fun execute(): CityWeatherList {
        val dailyWeatherResponse = WeatherRequest(cityName).execute()
        return covertToCityWeather(dailyWeatherResponse)
    }

    private fun covertToCityWeather(response: DailyWeatherResponse): CityWeatherList {
        val weather = response.HeWeather5[0]
        return CityWeatherList(weather.basic.city, weather.daily_forecast)
    }
}