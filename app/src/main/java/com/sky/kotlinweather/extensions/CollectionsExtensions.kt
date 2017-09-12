package com.sky.kotlinweather.extensions

/**
 * 集合扩展方法
 * 详述类的功能。
 * Created by sky on 2017/9/12.
 */

fun<K, V : Any> Map<K, V?>.toVarargsArray() :Array<out Pair<K, V>> =
        map { Pair(it.key, it.value!!) }.toTypedArray()

inline fun<T, R : Any> Iterable<T>.firstOrThrow(predicate : (T) -> R?) : R{
    forEach {
        val result = predicate(it)
        if(result != null) return result
    }
    throw NoSuchElementException("no element matching the predicate was found")
}