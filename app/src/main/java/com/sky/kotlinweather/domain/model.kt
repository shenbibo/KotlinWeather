package com.sky.kotlinweather.domain

import com.google.gson.Gson
import com.sky.kotlinweather.data.SearchCityResponse
import com.sky.slog.Slog
import com.sky.kotlinweather.data.CityInfo as ResponseCityInfo
/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2017/9/6.
 */

data class CityInfo(val name: String,
                    val country: String,
                    val location: String)


class CityRequest(private val cityName: String) : Command<SearchCityResponse> {
    override fun execute(): SearchCityResponse {
        val jsonCityInfo = java.net.URL("$URL&city=$cityName&key=$KEY").readText()
        Slog.i(jsonCityInfo)
        return Gson().fromJson(jsonCityInfo, SearchCityResponse::class.java)
    }

    companion object {
        private val KEY = "b06e0a9a06024ea0b09c4053b905b508"
        private val URL = "https://api.heweather.com/v5/search?"
    }
}

class RequestCityCommand {
    fun execute(cityNames: List<String>): List<CityInfo> {
        val searchCityList = arrayListOf<SearchCityResponse>()
        cityNames.forEach { searchCityList.add(CityRequest(it).execute()) }
        return convertToModelCityInfo(searchCityList)
    }

    private fun convertToModelCityInfo(cityResponseList: List<SearchCityResponse>): List<CityInfo> {
        return cityResponseList.map {
            val cityInfo = it.HeWeather5[0].basic
            CityInfo(cityInfo.city, cityInfo.cnty, "(${cityInfo.lat}, ${cityInfo.lon})")
        }
    }
}
