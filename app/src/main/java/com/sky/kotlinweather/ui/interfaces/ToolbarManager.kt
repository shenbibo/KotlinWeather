package com.sky.kotlinweather.ui.interfaces

import android.appwidget.AppWidgetHost
import android.graphics.drawable.Drawable
import android.support.v7.graphics.drawable.DrawerArrowDrawable
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View
import com.sky.kotlinweather.R
import com.sky.kotlinweather.base.App
import com.sky.kotlinweather.extensions.ctx
import com.sky.kotlinweather.extensions.slideEnter
import com.sky.kotlinweather.extensions.slideExit
import org.jetbrains.anko.toast

/**
 * 操纵toolbar的公共接口
 * 详述类的功能。
 * Created by sky on 2017/9/15.
 */
interface ToolbarManager {
    val toolbar: Toolbar

    var toolbarTitle: String
        get() = toolbar.title.toString()
        set(value) {
            toolbar.title = value
        }

    fun initToolbar() {
        toolbar.inflateMenu(R.menu.menu_main)
        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.actionSettings -> App.instance.toast("setting clicked")
                else -> App.instance.toast("unknow operate")
            }
            true
        }
    }

    /**
     * 设置oolbarbar图标和点击事件
     * */
    fun enableHomeAsUp(up: (View) -> Unit) {
        toolbar.navigationIcon = createArrowIcon()
        toolbar.setNavigationOnClickListener { up(it) }
    }

    // 方式一
//    private fun createArrowIcon() = with(DrawerArrowDrawable(toolbar.ctx)){
//        progress = 1f
//        this
//    }

    // 方式2使用apply
    private fun createArrowIcon() = DrawerArrowDrawable(toolbar.ctx).apply { progress = 1f }

    /**
     * 设置滑动时的动画效果
     * */
    fun attachToScroll(recyclerView: RecyclerView) {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                if (dy > 0) toolbar.slideExit() else toolbar.slideEnter()
            }
        })
    }
}