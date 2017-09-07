package com.sky.kotlinweather.data

/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2017/9/6.
 */
data class CityBaseInfo(val city: String,
                        val cnty: String,
                        val id: String,
                        val lat: String,
                        val lon: String,
                        val prov: String,
                        val update: UpdateTime)

data class Temperature(val max: Int, val min: Int)

data class UpdateTime(val loc: String, val utc: String)

data class DailyWeatherResponse(val HeWeather5: List<CityWeatherResponse>)

data class CityWeatherResponse(val basic: CityBaseInfo,
                               val daily_forecast: List<DailyWeather>,
                               val status: String)

data class WeatherStatus(val txt_d: String,
                         val txt_n: String)

data class DailyWeather(val date: String,
                        val cond: WeatherStatus,
                        val tmp: Temperature)
