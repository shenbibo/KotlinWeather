/**
 * context 扩展工具类
 * 详述类的功能。
 * Created by sky on 2017/9/14.
 */
package com.sky.kotlinweather.extensions

import android.content.Context
import android.support.v4.content.ContextCompat

fun Context.color(color: Int): Int = ContextCompat.getColor(this, color)