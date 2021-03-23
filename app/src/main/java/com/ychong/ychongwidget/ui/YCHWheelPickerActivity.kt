package com.ychong.ychongwidget.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ychong.library.YCHWheelPickerView
import com.ychong.library.dialog.ToastDialog
import com.ychong.ychongwidget.R
import com.ychong.ychongwidget.databinding.ActivityYchWheelPickerBinding

class YCHWheelPickerActivity:AppCompatActivity() {
    private var binding:ActivityYchWheelPickerBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_ych_wheel_picker)
        val list1:MutableList<String> = ArrayList()
        for (item in 0..100){
            list1.add("XXXXXXXX $item 号")
        }
        binding!!.ychWpv1.setDataList(list1)
        val list2:MutableList<String> = ArrayList()
            for (item in 0..100){
                list2.add("XXXXXXX $item 区")
            }
        binding!!.ychWpv2.setDataList(list2)

        binding!!.ychWpv1.setCur(10)


        binding!!.ychWpv1.setScrollChangedListener(object : YCHWheelPickerView.OnScrollChangedListener{
            override fun onScrollChanged(curIndex: Int) {
                ToastDialog(this@YCHWheelPickerActivity).setMsg(list1[curIndex]).build()
            }
        })
        binding!!.ychWpv2.setScrollChangedListener(object : YCHWheelPickerView.OnScrollChangedListener{
            override fun onScrollChanged(curIndex: Int) {
                ToastDialog(this@YCHWheelPickerActivity).setMsg(list2[curIndex]).build()
            }
        })
    }
}