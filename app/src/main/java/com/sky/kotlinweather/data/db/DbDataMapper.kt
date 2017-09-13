package com.sky.kotlinweather.data.db

import com.sky.kotlinweather.domain.CityWeatherList
import com.sky.kotlinweather.domain.DayWeather

/**
 * 数据库类与Domain类之间的转化
 * 详述类的功能。
 * Created by sky on 2017/9/12.
 */
object DbDataMapper {

    fun convertFormDomain(cityWeatherList: CityWeatherList) = with(cityWeatherList) {
        val dayWeatherPoList = dayWeather.map { convertDayWeatherFromDomain(id, it) }
        CityWeatherPo(id, cityName, country, dayWeatherPoList)
    }

    private fun convertDayWeatherFromDomain(id: String, dayWeather: DayWeather): DayWeatherPo =
            with(dayWeather) {
                DayWeatherPo(date, maxTmp, minTmp, desDaytime, desNight, id)
            }

    fun convertToDomain(cityWeatherPo: CityWeatherPo): CityWeatherList =
            with(cityWeatherPo) {
                val dayWeatherList = dayWeatherPo.map { convertDayToDomain(it) }
                CityWeatherList(id, cityName, country, dayWeatherList)
            }

    fun convertDayToDomain(dayWeatherPo: DayWeatherPo): DayWeather = with(dayWeatherPo) {
        DayWeather(date, maxTmp, minTmp, desDaytime, desNight)
    }


}