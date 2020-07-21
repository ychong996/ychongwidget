package com.ychong.library.utils

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build

/**
 * @author ychong
 * date: 2020/6/8
 * desc:
 */
object ResUtils {
    fun getColor(activity: Activity, color: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.resources.getColor(color, null)
        } else {
            TODO("VERSION.SDK_INT < M")
        }
    }
    fun getColor(context: Context, color: Int): Int {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            context.resources.getColor(color, null)
        } else {
            TODO("VERSION.SDK_INT < M")
        }
    }

    fun getDrawable(activity: Activity, drawable: Int): Drawable {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.resources.getDrawable(drawable, null)
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }
    }

    fun getDrawable(context: Context, drawable: Int): Drawable {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            context.resources.getDrawable(drawable, null)
        } else {
            TODO("VERSION.SDK_INT < LOLLIPOP")
        }
    }

    fun getText(context: Context, string: Int): String {
        return context.resources.getText(string).toString()

    }
}