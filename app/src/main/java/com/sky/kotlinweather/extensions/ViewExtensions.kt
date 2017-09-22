package com.sky.kotlinweather.extensions

import android.app.Fragment
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.sky.kotlinweather.base.App

/**
 * 一句话描述类的作用
 * 详述类的功能。
 * Created by sky on 2017/9/8.
 */
val View.ctx: Context
    get() = context

var TextView.textColor: Int
    get() = currentTextColor
    set(value) = setTextColor(value)

val ViewGroup.children: List<View>
    get() {
        val childes: MutableList<View> = ArrayList()
        for (i in 0 until childCount){
            childes[i] = getChildAt(i)
        }
        return childes
    }

fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

fun View.slideEnter() {
    if (translationY < 0f) animate().translationY(0f)
}