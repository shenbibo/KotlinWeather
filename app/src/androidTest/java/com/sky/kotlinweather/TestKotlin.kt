package com.sky.kotlinweather

import com.sky.slog.Slog

/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2017/9/9.
 */

/**
 * 所谓不可变，只是说它自己，但是它其中的字段对象的属性是否可变取决于字段对象本身
 * */
private fun testImmutableList() {
    val studentList = listOf(Student("sky", 28), Student("gavin", 27))
    studentList.forEach { it.name += "123" }
    studentList.forEach { Slog.i(it) }
}

data class Student(var name: String, var age: Int)

data class Person(val student: Student)