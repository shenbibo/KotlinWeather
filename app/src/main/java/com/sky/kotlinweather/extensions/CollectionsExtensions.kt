package com.sky.kotlinweather.extensions

/**
 * 集合扩展方法
 * 详述类的功能。
 * Created by sky on 2017/9/12.
 */

fun<K, V : Any> Map<K, V?>.toVarargsArray() :Array<out Pair<K, V>> =
        map { Pair(it.key, it.value!!) }.toTypedArray()