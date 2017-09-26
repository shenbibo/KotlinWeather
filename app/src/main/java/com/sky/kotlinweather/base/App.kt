package com.sky.kotlinweather.base

import android.app.Application
import com.sky.kotlinweather.extensions.DelegatesExt
import com.sky.kotlinweather.extensions.SetValueSingleNotNull
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
        var instance: App by DelegatesExt.notNullSingleValue()
            private set   // 设置为私有不允许设置
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}

// 使用单例来申明一个全局的对象
object App2 : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
