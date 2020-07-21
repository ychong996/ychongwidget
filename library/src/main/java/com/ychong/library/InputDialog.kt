package com.ychong.library

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.view.View
import com.ychong.library.databinding.DialogInputBinding


/**
 * @author ychong
 * date: 2020/5/27
 * desc:
 */
class InputDialog : Dialog, View.OnClickListener {
    private var binding: DialogInputBinding? = null
    private var onClickListener: OnClickListener? = null
    private var title:String? = null
    private var textLen = 16
    private var msg: String? = null
    private var height: Int? = null
    private var hintText:String? = null
    private var marginSpan: Int = 100
    private var dimAmount:Float = 0.2f
    fun setOnClickListener(onClickListener: OnClickListener?) {
        this.onClickListener = onClickListener
    }

    constructor(context: Context) : super(context) {}
    constructor(context: Context, marginSpan: Int) : super(context) {
        this.marginSpan = marginSpan
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogInputBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        initData()
        initListener()
    }

    private fun initData() {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        window?.setWindowAnimations(R.style.alpha_center_animation)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    private fun initListener() {
        binding!!.cancelTv.setOnClickListener(this)
        binding!!.confirmTv.setOnClickListener(this)
        binding!!.textEt.onFocusChangeListener = View.OnFocusChangeListener { p0, p1 ->
            if (p1) {
                binding!!.clearTextIv.visibility = View.VISIBLE
            } else {
                binding!!.clearTextIv.visibility = View.INVISIBLE
            }
        }
        binding!!.clearTextIv.setOnClickListener(this)
    }

    fun setTitle(title:String?):InputDialog{
        if (title.isNullOrEmpty())return this
        this.title = title
        return this
    }
    fun setMsg(msg: String?): InputDialog {
        if (msg.isNullOrEmpty()) return this
        this.msg = msg
        return this
    }

    fun setMsgLen(textLen: Int?): InputDialog {
        if (textLen == null) return this
        this.textLen = textLen
        return this
    }

    fun setHeight(height: Int?): InputDialog {
        if (height == null) return this
        this.height = height
        return this
    }
    fun setHintText(hintText:String?):InputDialog{
        if (hintText.isNullOrEmpty())return this
        this.hintText = hintText
        return this
    }

    fun setDimAmount(dimAmount:Float?):InputDialog{
        if (dimAmount==null||dimAmount<0||dimAmount>1)return this
        this.dimAmount = dimAmount
        return this
    }
    fun build(): InputDialog {
        show()
        setViewData()
        return this
    }

    private fun setViewData() {
        if (title.isNullOrEmpty()){
            binding!!.titleTv.visibility = View.GONE
        }else{
            binding!!.titleTv.text = this.title
        }
        window?.setDimAmount(this.dimAmount)
        binding!!.textEt.filters = arrayOf<InputFilter>(LengthFilter(textLen))
        binding!!.textEt.setText(msg)
        if (this.height != null) {
            binding!!.textEt.layoutParams.height = this.height!!
        }
        binding!!.textEt.hint = this.hintText

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.cancelTv -> {
                if (onClickListener != null) {
                    onClickListener!!.cancel()
                }
                dismiss()
            }
            R.id.confirmTv -> {
                if (onClickListener != null) {
                    onClickListener!!.confirm(binding!!.textEt.text.toString())
                }
                dismiss()
            }
            R.id.clearTextIv -> {
                binding!!.textEt.text = null
            }
        }
    }

    interface OnClickListener {
        fun cancel()
        fun confirm(remark: String?)
    }
}