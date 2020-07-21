package com.ychong.library

import android.app.Dialog
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import com.ychong.library.databinding.DialogTipsBinding

/**
 * @author ychong
 * date: 2020/5/27
 * desc:
 */
class TipsDialog(context: Context) : Dialog(context), View.OnClickListener {
    private  var binding: DialogTipsBinding?=null
    private  var onClickListener: OnClickListener?=null
    private var marginSpan: Int = 100

    private var tips:String? = null
    private var tipsStyle:Int? = Typeface.NORMAL
    fun setTipsListener(onClickListener: OnClickListener?) {
        this.onClickListener = onClickListener
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogTipsBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.confirmTv.setOnClickListener(this)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        window?.setWindowAnimations(R.style.alpha_center_animation)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }
    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.confirmTv) {
            if (onClickListener != null) {
                onClickListener!!.click()
            }
            dismiss()
        }
    }

    fun setMsg(msg:String?):TipsDialog{
        this.tips = msg
        return this
    }
    fun setMsgStyle(msgStyle:Int?):TipsDialog{
        if (msgStyle==null)return this
        this.tipsStyle = tipsStyle
        return this
    }
    fun build() : TipsDialog{
        show()
        setViewData()
        return this
    }
    private fun setViewData(){
        binding!!.tipsTv.text = tips
        if (tipsStyle!=null){
            binding!!.tipsTv.setTypeface(Typeface.DEFAULT,tipsStyle!!)
        }

    }

}