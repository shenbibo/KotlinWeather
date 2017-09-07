package com.sky.kotlinweather.data

/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2017/9/7.
 */
data class SearchCityResponse(val HeWeather5: List<CityResponse>)

data class CityResponse(val basic: CityInfo, val status: String)

data class CityInfo(val city: String,
                    val cnty: String,
                    val id: String,
                    val lat: String,
                    val lon: String,
                    val prov: String)