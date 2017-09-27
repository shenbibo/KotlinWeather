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

val ViewGroup.children: Iterable<View>
    get() = (0 until childCount).map { getChildAt(it) }

fun View.slideExit() {
    if (translationY == 0f) animate().translationY(-height.toFloat())
}

fun View.slideEnter() {
    if (translationY < 0f) animate().translationY(0f)
}