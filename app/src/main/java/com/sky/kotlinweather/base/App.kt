package com.sky.kotlinweather.base

import android.app.Application
import android.widget.TextView
import com.sky.kotlinweather.extensions.DelegatesExt

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
        var list: List<Int> = ArrayList()
        for (index in list.indices) print(list.get(index))

        // .. 表示的是是rangeTo函数，返回的是一个 *Range 对象
        for (i in 1..4) print(i) // 输出“1234”
        for (i in 4..1) print(i) // 什么都不输出

        // 反转后迭代
        for (i in (1..4).reversed()) print(i)  // 输出“4321”
        // 倒序迭代
        for (i in 4 downTo 1) print(i) // 输出“4321”

        // 指定迭代步长
        for (i in 4 downTo 1 step 2) print(i) // 输出“42”

        // 不包括末尾元素
        for (i in 1 until 10) print(i)   // i in [1, 10) 排除了 10
    }
}

fun whenTest(x: Any) = when (x) {
    0, 1 -> x
    in 2..10 -> x.toString() + 10
    !in 11..30 -> print("$x is not in 11..30")
    is String -> print(x.length)
    is TextView -> x.text.toString()
    !is Long -> print("$x is not a Long")
    else -> false
}

open class Person constructor(private var name: String, age: Int) {
    var firstName: String = name.substring(0, 2)

    init {
        println("Person init is called")
    }

    // 子类可以复写
    open var age = age

    open fun foo() {
        println("this is person foo $name")
    }
}

class Engineer(name: String, age: Int, private var skillList: MutableList<String>) : Person(name, age) {
    // 工程师消耗脑力过渡，年轮+5
    override var age = super.age + 5

    // 初始化代码，使用主构造函数时，会调用
    init {
        println("Engineer init is called")
    }

    constructor(name: String, age: Int) : this(name, age, ArrayList()) {
        skillList.add("java")
        skillList.add("kotlin")
        skillList.add("c++")
    }

    override fun foo() {
        println("this is Engineer foo")
    }
}
