package com.sky.kotlinweather.domain

/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2017/9/6.
 */
interface Command<out T> {
    fun execute(): T
}