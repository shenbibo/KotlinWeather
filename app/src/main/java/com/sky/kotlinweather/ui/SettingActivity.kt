package com.sky.kotlinweather.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem

import com.sky.kotlinweather.R
import com.sky.kotlinweather.base.App
import com.sky.kotlinweather.extensions.DelegatesExt

import kotlinx.android.synthetic.main.activity_setting.*
import kotlinx.android.synthetic.main.toolbar_layout.*

class SettingActivity : AppCompatActivity() {

    companion object {
        val CITY_NAME = "cityName"
        val DEFAULT_CITY = "深圳"
    }

    private var cityName: String by DelegatesExt.preference(App.instance, CITY_NAME, DEFAULT_CITY)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        inputCity.setText(cityName)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        android.R.id.home -> {
            onBackPressed()
            true
        }
        else -> false
    }

    override fun onBackPressed() {
        cityName = inputCity.text.toString()
        super.onBackPressed()
    }
}
