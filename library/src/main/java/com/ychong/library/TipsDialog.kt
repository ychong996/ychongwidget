package com.ychong.library

import android.app.Dialog
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import com.ychong.library.databinding.DialogTipsBinding

class TipsDialog(context: Context) : Dialog(context) {
    private var binding: DialogTipsBinding? = null
    private var onClickListener: OnClickListener? = null
    private var title: String? = null
    private var titleSize: Float? = 10f
    private var titleColor:Int? = 0
    private var titleStyle: Int? = Typeface.NORMAL
    private var tips: String? = null
    private var tipsSize: Float? = 10f
    private var tipsColor:Int? = 0
    private var tipsStyle: Int? = Typeface.NORMAL
    fun setOnClickListener(onClickListener: OnClickListener?) {
        this.onClickListener = onClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogTipsBinding.inflate(layoutInflater)
    }

    fun setTitle(title: String?): TipsDialog {
        if (title.isNullOrBlank() || title.isEmpty())
            return this
        this.title = title
        return this
    }

    fun setTitleSize(titleSize: Float?): TipsDialog {
        if (titleSize==null||titleSize<=0)
            return this
        this.titleSize = titleSize
        return this
    }
    fun setTitleColor(titleColor:Int?):TipsDialog{
        if (titleColor==null||titleColor<=0)return this
        this.titleColor = titleColor
        return this
    }

    fun setTitleStyle(titleStyle: Int?): TipsDialog {
        if (titleStyle==null||titleStyle<=0)return this
        this.titleStyle = titleStyle
        return this
    }

    fun setTips(tips: String?): TipsDialog {
        if (tips.isNullOrBlank()||tips.isEmpty())return this
        this.tips = tips
        return this
    }

    fun setTipsSize(tipsSize: Float?): TipsDialog {
        if (tipsSize==null||tipsSize<=0f)return this
        this.tipsSize = tipsSize
        return this
    }
    fun setTipsColor(tipsColor:Int?):TipsDialog{
        if (tipsColor==null||tipsColor<=0)return this
        this.tipsColor = tipsColor
        return this
    }

    fun setTipsStyle(tipsStyle: Int?): TipsDialog {
        if (tipsStyle==null||tipsStyle<=0)return this
        this.tipsStyle = tipsStyle
        return this
    }

    fun build(): TipsDialog {
        show()
        setViewData()
        return this
    }

    private fun setViewData() {
        binding!!.titleTv.text = title
        binding!!.titleTv.setTypeface(Typeface.DEFAULT,titleStyle!!)
        binding!!.titleTv.setTextColor(titleColor!!)
        binding!!.titleTv.textSize = titleSize!!


        binding!!.tipsTv.text = tips
        binding!!.tipsTv.setTypeface(Typeface.DEFAULT,tipsStyle!!)
        binding!!.tipsTv.setTextColor(tipsColor!!)
        binding!!.tipsTv.textSize = tipsSize!!


    }


}