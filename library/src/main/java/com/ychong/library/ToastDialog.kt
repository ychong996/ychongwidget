package com.ychong.library

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import com.ychong.library.databinding.DialogToastBinding

/**
 * @author ychong
 * date: 2020/6/3
 * desc:
 */
class ToastDialog(context: Context) : Dialog(context) {
    private  var binding: DialogToastBinding?=null
    private var msg: String? = null
    private val handler = Handler()
    private var delayTime: Long? =
        SHORT_TIME


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogToastBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        window?.setDimAmount(0.1f)
        window?.setWindowAnimations(R.style.alpha_center_animation)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun setMsg(msg:String?): ToastDialog {
        if (msg.isNullOrEmpty())return this
        this.msg = msg
        return this
    }
    fun setDelayTime(delayTime:Long?): ToastDialog {
        if (delayTime==null)return this
        this.delayTime = delayTime
        return this
    }

    fun build(): ToastDialog {
        show()
        setViewDate()
        return this
    }
    private fun setViewDate(){
        binding!!.tipsTv.text = msg
        handler.postDelayed(runnable, delayTime!!)
    }



    private var runnable = Runnable { dismiss() }

    companion object {
        const val SHORT_TIME: Long = 2000
        const val LONG_TIME: Long = 3000
    }
}