package com.sky.kotlinweather.data.db

import com.antonioleiva.weatherapp.extensions.parseList
import com.antonioleiva.weatherapp.extensions.parseOpt
import com.sky.kotlinweather.domain.CityWeatherList
import com.sky.kotlinweather.domain.WeatherDataSource
import com.sky.kotlinweather.extensions.toVarargsArray
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

/**
 * 数据库操作类
 * 详述类的功能。
 * Created by sky on 2017/9/12.
 */
class WeatherDb(private val dbHelper: WeatherDbHelper = WeatherDbHelper.instance,
                private val dataMapper: DbDataMapper = DbDataMapper) : WeatherDataSource {

    override fun requestCityWeatherByCityName(cityName: String): CityWeatherList? = dbHelper.use {
        val selectCity = "${CityInfoTable.CITY_NAME} = ?"
        val tempCityWeatherPo = select(CityInfoTable.NAME)
                .whereSimple(selectCity, cityName)
                .parseOpt {
                    CityWeatherPo(HashMap(it), arrayListOf())
                } ?: return@use null

        val selectDayWeather = "${DayWeatherTable.CITY_ID} = ?"
        val dayWeatherPoList = select(DayWeatherTable.NAME)
                .whereSimple(selectDayWeather, tempCityWeatherPo.id)
                .parseList { DayWeatherPo(HashMap(it)) }

        val cityWeatherPo = tempCityWeatherPo.copy(dayWeatherPo = dayWeatherPoList)
        dataMapper.convertToDomain(cityWeatherPo)
    }

    fun saveCityWeather(cityWeatherList: CityWeatherList) = dbHelper.use {
        deleteOldCityWeather(cityWeatherList.id)

        with(dataMapper.convertFormDomain(cityWeatherList)) {
            insert(CityInfoTable.NAME, *map.toVarargsArray())
            dayWeatherPo.forEach { insert(DayWeatherTable.NAME, *it.map.toVarargsArray()) }
        }
    }

    private fun deleteOldCityWeather(cityId: String) = dbHelper.use {
        delete(CityInfoTable.NAME, "${CityInfoTable.ID} = ?", arrayOf(cityId))
        delete(DayWeatherTable.NAME, "${DayWeatherTable.CITY_ID} = ?", arrayOf(cityId))
    }
}