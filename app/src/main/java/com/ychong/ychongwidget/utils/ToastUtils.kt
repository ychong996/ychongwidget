package com.ychong.ychongwidget.utils

import android.content.Context
import android.widget.Toast

/**
 * @author ychong
 * date: 2020/5/25
 * desc: toast在某些机型中，会带有应用信息前缀
 */
object ToastUtils {
    fun showText(context: Context?, text: String?) {
        val toast = Toast.makeText(context, null, Toast.LENGTH_SHORT)
        toast.setText(text)
        toast.show()
    }
}