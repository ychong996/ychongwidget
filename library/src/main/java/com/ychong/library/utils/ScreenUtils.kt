package com.ychong.library.utils

import android.app.Activity
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager

/**
 * @author ychong
 * date: 2020/6/1
 * desc:
 */
object ScreenUtils {
    private val sDM = Resources.getSystem().displayMetrics
    val screenWidth: Int
        get() = sDM.widthPixels

    val screenHeight: Int
        get() = sDM.heightPixels

    val density: Float
        get() = sDM.density

    fun dpToPx(dp: Int): Int {
        return dpToPx(dp.toFloat())
    }

    fun dpToPx(dp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, sDM).toInt()
    }

    fun spToPx(sp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, sDM).toInt()
    }

    fun pxToDp(px: Int): Int {
        return Math.round(px / density)
    }

    fun getScreenWidth(activity: Activity): Int {
        val manager: WindowManager = activity.windowManager
        val outMetrics = DisplayMetrics()
        manager.defaultDisplay.getMetrics(outMetrics)
        return outMetrics.widthPixels
    }
}