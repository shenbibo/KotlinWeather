package com.sky.kotlinweather.domain

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.sky.kotlinweather.base.App
import org.jetbrains.anko.db.*

/**
 * 数据库操作相关类
 * 详述类的功能。
 * Created by sky on 2017/9/9.
 */
class WeatherDbHelper(ctx: Context = App.instance)
    : ManagedSQLiteOpenHelper(ctx, DB_NAME, null, VERSION) {
    companion object {
        val DB_NAME = "weather.db"
        val VERSION = 1
        val instance: WeatherDbHelper? by lazy { WeatherDbHelper() }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        createCityInfoTableIfNotExist(db)
        createDayWeatherTableIfNotExist(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, old: Int, new: Int) {
        db?.dropTable(CityInfoTable.NAME, true)
        db?.dropTable(DayWeatherTable.NAME, true)
        onCreate(db)
    }

    private fun createCityInfoTableIfNotExist(db: SQLiteDatabase?) {
        db?.createTable(CityInfoTable.NAME, true,
                        CityInfoTable.ID to TEXT + PRIMARY_KEY,
                        CityInfoTable.CITY_NAME to TEXT,
                        CityInfoTable.COUNTRY to TEXT,
                        CityInfoTable.LAT to TEXT,
                        CityInfoTable.LON to TEXT)
    }

    private fun createDayWeatherTableIfNotExist(db: SQLiteDatabase?) {
        db?.createTable(DayWeatherTable.NAME, true,
                        DayWeatherTable.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                        DayWeatherTable.CITY_ID to TEXT,
                        DayWeatherTable.DATE to TEXT,
                        DayWeatherTable.HIGH to INTEGER,
                        DayWeatherTable.LOW to INTEGER,
                        DayWeatherTable.DES_DAYTIME to TEXT,
                        DayWeatherTable.DES_NIGHT to TEXT)
    }
}


object CityInfoTable {
    val NAME = "CityInfo"
    val ID = "id"
    val CITY_NAME = "cityName"
    val COUNTRY = "country"
    val LON = "lon"  // 经度
    val LAT = "lat"  // 纬度
}

object DayWeatherTable {
    val NAME = "DayWeather"
    val ID = "id"
    val DATE = "date"
    val HIGH = "high"
    val LOW = "low"
    val DES_DAYTIME = "desDaytime" // 白天天气描述
    val DES_NIGHT = "desNight"     // 夜晚天气描述
    val CITY_ID = "cityId"
}