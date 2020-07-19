package com.ychong.ychongwidget

object ListUtils {
    fun getList():MutableList<String>{
        val list:MutableList<String> = ArrayList()
        list.add("LoadingDialog")
        list.add("TipsDialog")
        list.add("InputDialog")
        list.add("MessageDialog")
        list.add("ResultDialog")
        list.add("未知Dialog")
        return list
    }
}