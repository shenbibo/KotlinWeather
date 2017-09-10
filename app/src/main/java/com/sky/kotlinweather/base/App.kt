package com.sky.kotlinweather.base

import android.app.Application
import kotlin.properties.Delegates
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2017/9/9.
 */
class App : Application() {

    companion object {
        var instance: App by SetValueSingleNotNull()
            private set   // 设置为私有不允许设置
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}

// 自定义委托，不允许重复设置值
class SetValueSingleNotNull<T> : ReadWriteProperty<Any?, T> {
    private var value: T? = null

    override fun getValue(thisRef: Any?, property: KProperty<*>): T =
            value ?: throw IllegalStateException("must be init first before get")

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = if (this.value == null) value
        else throw IllegalStateException("${property.name} already initialized")
    }
}


// 使用单例来申明一个全局的对象
object App2 : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}