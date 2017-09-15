package com.sky.kotlinweather.extensions

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2017/9/15.
 */
object DelegatesExt {
    fun <T> notNullSingleValue() = SetValueSingleNotNull<T>()
    fun <T : Any> preference(context: Context, name: String, default: T) = Preference(context, name, default)
}


// 自定义委托，不允许重复设置值
// 注意也可以不继承ReadWriteProperty，自己写这些方法，不过要使用operator
class SetValueSingleNotNull<T> : ReadWriteProperty<Any?, T> {
    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T =
            value ?: throw IllegalStateException("must be init first before get")

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}

class Preference<T : Any>(private val ctx: Context,
                          private val name: String,
                          private val value: T) : ReadWriteProperty<Any?, T> {

    private val prefs: SharedPreferences by lazy { ctx.getSharedPreferences("default", Context.MODE_PRIVATE) }


    override fun getValue(thisRef: Any?, property: KProperty<*>): T = getValue(name, value)

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        setValue(name, value)
    }

    @Suppress("UNCHECKED_CAST", "IMPLICIT_CAST_TO_ANY")
    private fun getValue(name: String, value1: T): T = with(prefs) {
        when (value1) {
            is Long -> getLong(name, value1)
            is Int -> getInt(name, value1)
            is String -> getString(name, value1)
            is Boolean -> getBoolean(name, value1)
            is Float -> getFloat(name, value1)
            else -> throw  IllegalArgumentException("not support type = " + value1::class.java.name)
        } as T
    }

    @SuppressLint("CommitPrefEdits")
    private fun setValue(name: String, value: T) = with(prefs.edit()) {
        when (value) {
            is Long -> putLong(name, value)
            is Int -> putInt(name, value)
            is String -> putString(name, value)
            is Boolean -> putBoolean(name, value)
            is Float -> putFloat(name, value)
            else -> throw  IllegalArgumentException("not support type = " + value::class.java.name)
        }.commit()
    }
}