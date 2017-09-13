package com.sky.kotlinweather.domain

import com.sky.kotlinweather.data.db.WeatherDb
import com.sky.kotlinweather.data.server.WeatherServer
import com.sky.kotlinweather.extensions.firstOrThrow

/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2017/9/12.
 */
class WeatherProvider(private val sources: List<WeatherDataSource> = SOURCES)
    : WeatherDataSource {
    companion object {
        val SOURCES = listOf(WeatherDb(), WeatherServer())
    }

    override fun requestDetailByDate(cityId: String, date: String): DayWeather?
            = sources.firstOrThrow { it.requestDetailByDate(cityId, date) }

    override fun requestCityWeatherByCityName(cityName: String): CityWeatherList?
            = sources.firstOrThrow { it.requestCityWeatherByCityName(cityName) }

}