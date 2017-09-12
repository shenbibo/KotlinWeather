package com.sky.kotlinweather.data.server

import com.google.gson.Gson
import com.sky.kotlinweather.data.db.WeatherDb
import com.sky.kotlinweather.domain.CityWeatherList
import com.sky.kotlinweather.domain.WeatherDataSource
import com.sky.slog.Slog
import java.net.URL

/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2017/9/12.
 */
class WeatherServer(private val dataMapper: ServerDataMapper = ServerDataMapper,
                    private val weatherDb: WeatherDb = WeatherDb()) : WeatherDataSource {

    override fun requestCityWeatherByCityName(cityName: String): CityWeatherList? {
        val jsonCityWeather = URL("$BASE_URL_FREE$WEATHER_METHOD?city=$cityName&key=$KEY").readText()
        Slog.json(jsonCityWeather)
        val weatherRep = Gson().fromJson(jsonCityWeather, DailyWeatherResponse::class.java)
        val cityWeatherList = dataMapper.convertToCityWeatherList(weatherRep)
        weatherDb.saveCityWeather(cityWeatherList)
        return cityWeatherList
    }
}