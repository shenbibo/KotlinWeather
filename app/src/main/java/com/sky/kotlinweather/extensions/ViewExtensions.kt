package com.sky.kotlinweather.extensions

import android.content.Context
import android.view.View
import android.widget.TextView

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