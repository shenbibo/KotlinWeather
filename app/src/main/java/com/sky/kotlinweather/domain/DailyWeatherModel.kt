package com.sky.kotlinweather.domain

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


class GetCityWeatherCommand(private val cityName: String,
                            private val provider: WeatherProvider = WeatherProvider()) : Command<CityWeatherList> {
    override fun execute(): CityWeatherList {
        val cityWeatherList = provider.requestCityWeatherByCityName(cityName)
        return cityWeatherList?:throw NoSuchElementException("no matching weather matching for the $cityName")
    }

}