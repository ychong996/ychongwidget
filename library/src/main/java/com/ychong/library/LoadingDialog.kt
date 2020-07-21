package com.ychong.library

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.ychong.library.databinding.DialogLoadingBinding

class LoadingDialog(context: Context) : Dialog(context) {
    private var rotateAnimation: RotateAnimation? = null

    //private var animation: Animation? = null
    private var binding: DialogLoadingBinding? = null
    private var text: String? = null
    private var loadingRes: Int = -1
    private var backgroundColor: Int = -1
    private var width: Float = -1f
    private var height: Float = -1f
    private var delayTime: Long = 3000
    private var dimAmount:Float = 0.2f
    private var isCan:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogLoadingBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
    }

    private fun initRotate() {
        //动画
        //animation = AnimationUtils.loadAnimation(context, R.anim.rotate)
        rotateAnimation = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_PARENT,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )
        rotateAnimation!!.duration = this.delayTime
        rotateAnimation!!.repeatMode = Animation.RESTART
        rotateAnimation!!.repeatCount = Animation.INFINITE

        val lin = LinearInterpolator() //设置动画匀速运动
        rotateAnimation!!.interpolator = lin
        binding!!.loadingIv.startAnimation(rotateAnimation)
    }

    fun setLoadingImg(loadingRes: Int): LoadingDialog {
        this.loadingRes = loadingRes
        return this
    }

    fun setDelayTime(delayTime: Long): LoadingDialog {
        this.delayTime = delayTime
        return this
    }

    fun setText(text: String?): LoadingDialog {
        this.text = text
        return this
    }

    fun setBackgroundColor(backgroundColor: Int): LoadingDialog {
        this.backgroundColor = backgroundColor
        return this
    }
    fun isCan(isCan:Boolean?):LoadingDialog{
        if (isCan==null)return this
        this.isCan = isCan
        return this
    }
    fun setDimAmount(dimAmount:Float?):LoadingDialog{
        if (dimAmount==null||dimAmount<0||dimAmount>1)return this
        this.dimAmount = dimAmount
        return this
    }

    fun build() {
        show()
        setViewData()
    }

    private fun setViewData() {
        setCancelable(this.isCan)
        setCanceledOnTouchOutside(this.isCan)
        window?.attributes?.gravity = Gravity.CENTER
        window?.setWindowAnimations(R.style.alpha_center_animation)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        window?.setDimAmount(this.dimAmount)
        binding!!.loadingTv.text = this.text
        binding!!.loadingIv.setImageResource(this.loadingRes)
        binding!!.loadingLayout.setBackgroundColor(this.backgroundColor)
        rotateAnimation!!.duration = this.delayTime
        initRotate()
    }

    override fun dismiss() {
        if (rotateAnimation != null) {
            rotateAnimation!!.cancel()
            rotateAnimation = null
        }
        super.dismiss()
    }
}