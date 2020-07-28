package com.ychong.library.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import com.ychong.library.R
import com.ychong.library.databinding.DialogLoadingBinding
import com.ychong.library.utils.ResUtils

class LoadingDialog(context: Context) : Dialog(context) {
    private var animation: Animation? = null
    private var binding: DialogLoadingBinding? = null
    private var text: String? = "请稍候..."
    private var textColor:Int? = null
    private var loadingRes: Int? =null
    private var backgroundColor: Int? =null
    private var background:Int? =
        R.drawable.bg_333333_side_radius
    private var width: Float? = null
    private var height: Float? = null
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
        animation = AnimationUtils.loadAnimation(context,
            R.anim.rotate
        )
        val lin = LinearInterpolator() //设置动画匀速运动

        animation!!.interpolator = lin
        binding!!.loadingIv.startAnimation(animation)

    }

    fun setLoadingImg(loadingRes: Int?): LoadingDialog {
        if (loadingRes==null)return this
        this.loadingRes = loadingRes
        return this
    }

    fun setDelayTime(delayTime: Long?): LoadingDialog {
        if (delayTime==null)return this
        this.delayTime = delayTime
        return this
    }

    fun setText(text: String?): LoadingDialog {
        this.text = text
        return this
    }
    fun setTextColor(textColor:Int?): LoadingDialog {
        if (textColor==null)return this
        this.textColor = textColor
        return this
    }

    fun setBackgroundColor(backgroundColor: Int?): LoadingDialog {
        if (backgroundColor==null)return this
        this.backgroundColor = backgroundColor
        return this
    }
    fun setBackground(background:Int?): LoadingDialog {
        if (background == null)return this
        this.background = background
        return this
    }
    fun isCan(isCan:Boolean?): LoadingDialog {
        if (isCan==null)return this
        this.isCan = isCan
        return this
    }
    fun setDimAmount(dimAmount:Float?): LoadingDialog {
        if (dimAmount==null||dimAmount<0||dimAmount>1)return this
        this.dimAmount = dimAmount
        return this
    }
    fun setWidth(width:Float?): LoadingDialog {
        if (width == null)return this
        this.width = width
        return this
    }
    fun setHeight(height:Float?): LoadingDialog {
        if (height==null)return this
        this.height = height
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
        if (text.isNullOrEmpty()){
            binding!!.loadingTv.visibility= View.GONE
        }
        binding!!.loadingTv.text = this.text
        if (this.textColor!=null){
            binding!!.loadingTv.setTextColor(ResUtils.getColor(context,this.textColor!!))
        }
        if (this.loadingRes!=null){
            binding!!.loadingIv.setImageResource(this.loadingRes!!)
        }
        if (this.backgroundColor!=null){
            binding!!.loadingLayout.setBackgroundColor(ResUtils.getColor(context,this.backgroundColor!!))
        }
        if (this.background!=null){
            binding!!.loadingLayout.background = ResUtils.getDrawable(context,this.background!!)
        }
        initRotate()

    }

    override fun dismiss() {
        if (animation != null) {
            animation!!.cancel()
            animation = null
        }
        super.dismiss()
    }
}