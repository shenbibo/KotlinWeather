package com.sky.kotlinweather.data.server

import com.sky.kotlinweather.domain.CityWeatherList
import com.sky.kotlinweather.domain.DayWeather

/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2017/9/12.
 */
object ServerDataMapper {

    fun convertToCityWeatherList(response: DailyWeatherResponse): CityWeatherList =
            with(response.HeWeather5[0]) {
                CityWeatherList(basic.id, basic.city, basic.cnty, convertToDayWeather(daily_forecast))
            }


    private fun convertToDayWeather(dailyForecastList: List<DailyWeather>): List<DayWeather> =
            dailyForecastList.map {
                with(it) {
                    DayWeather(date, tmp.max, tmp.min, cond.txt_d, cond.txt_n)
                }
            }
}