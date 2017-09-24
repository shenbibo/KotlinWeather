/**
 * context 扩展工具类
 * 详述类的功能。
 * Created by sky on 2017/9/14.
 */
package com.sky.kotlinweather.extensions

import android.content.Context
import android.support.v4.content.ContextCompat
import android.widget.Toast

fun Context.color(color: Int): Int = ContextCompat.getColor(this, color)

// 而在kotlin中我们只需要定义一个Context的扩展方法
fun Context.toast(msg: String, duration: Int = Toast.LENGTH_LONG){
    Toast.makeText(this, msg,duration).show();
}