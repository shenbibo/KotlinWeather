package com.sky.kotlinweather

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.sky.slog.LogcatTree
import com.sky.slog.Slog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val weatherData = listOf("beijing 39", "shanghai 27", "shenzhen 88")

    override fun onCreate(savedInstanceState: Bundle?) {
        Slog.init(LogcatTree()).simpleMode(true)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        weather_list.layoutManager = LinearLayoutManager(this)
        weather_list.adapter = WeatherListAdapter(weatherData)

        testImmutableList()
    }

    /**
     * 所谓不可变，只是说它自己，但是它其中的字段可不可以变取决于字段本身
     * */
    private fun testImmutableList(){
        val studentList = listOf(Student("sky", 28), Student("gavin", 27))
        studentList.forEach { it.name += "123" }
        studentList.forEach { Slog.i(it)}
    }

    data class Student(var name : String, var age : Int)
}
