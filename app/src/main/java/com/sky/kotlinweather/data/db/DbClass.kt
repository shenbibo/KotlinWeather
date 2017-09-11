package com.sky.kotlinweather.data.db

/**
 * 数据库相关数据类
 * 详述类的功能。
 * Created by sky on 2017/9/11.
 */

class CityWeatherPo(map: MutableMap<String, Any?>, val dayWeather: List<DayWeatherPo>) {
    var id: String by map
    var cityName: String by map
    var country: String by map

    constructor(id: String,
                cityName: String,
                country: String,
                dayWeather: List<DayWeatherPo>) : this(HashMap(), dayWeather) {
        this.id = id
        this.cityName = cityName
        this.country = country
    }

}

class DayWeatherPo(map: MutableMap<String, Any?>) {
    var id: String by map
    var date: String by map
    var maxTmp: Int by map
    var minTmp: Int by map
    var desDaytime: String by map
    var desNight: String by map
    var cityId: String by map

    constructor(date: String,
                maxTmp: Int,
                minTmp: Int,
                desDaytime: String,
                desNight: String,
                cityId: String) : this(HashMap()) {
        this.date = date
        this.maxTmp = maxTmp
        this.minTmp = minTmp
        this.desDaytime = desDaytime
        this.desNight = desNight
        this.cityId = cityId
    }
}