package com.ychong.library.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import com.ychong.library.R
import com.ychong.library.databinding.DialogResultBinding
import com.ychong.library.listener.OnListener
import com.ychong.library.utils.ResUtils

/**
 * @author ychong
 * date: 2020/6/3
 * desc:
 */
class ResultDialog(context: Context) : Dialog(context) {
    private var binding: DialogResultBinding? = null
    private var tips: String? = null
    private var resultImg: Int? = null
    private val handler = Handler()
    private var delayTime: Long =
        SHORT_TIME
    private var onListener: OnListener? = null
    fun setOnListener(onListener: OnListener): ResultDialog {
        this.onListener = onListener
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogResultBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        val window = window
        window?.setWindowAnimations(R.style.alpha_center_animation)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun setTips(tips: String?): ResultDialog {
        if (tips.isNullOrEmpty()) return this
        this.tips = tips
        return this
    }

    fun setResultImg(resultImg: Int?): ResultDialog {
        if (resultImg == null) return this
        this.resultImg = resultImg
        return this
    }

    fun setDelayTime(delayTime: Long?): ResultDialog {
        if (delayTime == null) return this
        this.delayTime = delayTime
        return this
    }

    fun build(): ResultDialog {
        show()
        setViewData()
        return this
    }

    private fun setViewData() {
        binding!!.tipsTv.text = this.tips
        if (this.resultImg != null) {
            binding!!.resultIv.setImageDrawable(ResUtils.getDrawable(context, this.resultImg!!))
        }
        handler.postDelayed(runnable, delayTime)
    }

    private var runnable = Runnable {
        if (onListener != null) {
            onListener!!.listener()
        }
        dismiss()
    }

    companion object {
        const val SHORT_TIME: Long = 2000
        const val LONG_TIME: Long = 3000
    }
}