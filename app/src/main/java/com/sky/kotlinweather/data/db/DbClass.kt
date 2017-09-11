package com.sky.kotlinweather.data.db

/**
 * 数据库相关数据类
 * 详述类的功能。
 * Created by sky on 2017/9/11.
 */

class CityWeatherPo(val map: MutableMap<String, Any?>, val dailWeather: List<DayWeatherPo>) {

}

class DayWeatherPo(var map: MutableMap<String, Any?>) {
    var id: String by map
    var date:String by map
//    var
}