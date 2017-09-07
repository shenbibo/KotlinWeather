package com.sky.kotlinweather.domain

import com.google.gson.Gson
import com.sky.kotlinweather.data.*
import com.sky.slog.Slog
import java.net.URL
import com.sky.kotlinweather.data.CityInfo as ResponseCityInfo
/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2017/9/6.
 */

data class City(val name: String,
                val country: String,
                val location: String)


class CityRequest(private val cityName: String) : Command<SearchCityResponse> {
    override fun execute(): SearchCityResponse {
        val jsonCityInfo = URL("$BASE_URL$METHOD?&city=$cityName&key=$KEY").readText()
        Slog.json(jsonCityInfo)
        return Gson().fromJson(jsonCityInfo, SearchCityResponse::class.java)
    }

    companion object {
        val METHOD = "search"
    }
}

class RequestCityCommand {
    fun execute(cityNames: List<String>): List<City> {
        val searchCityList = arrayListOf<SearchCityResponse>()
        cityNames.forEach { searchCityList.add(CityRequest(it).execute()) }
        return convertToModelCityInfo(searchCityList)
    }

    private fun convertToModelCityInfo(cityResponseList: List<SearchCityResponse>): List<City> {
        return cityResponseList.map {
            val cityInfo = it.HeWeather5[0].basic
            City(cityInfo.city, cityInfo.cnty, "(${cityInfo.lat}, ${cityInfo.lon})")
        }
    }
}
