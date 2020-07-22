package com.ychong.library.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.util.TypedValue
import android.view.View
import com.ychong.library.R
import com.ychong.library.databinding.DialogInputBinding
import com.ychong.library.listener.OnLeftListener
import com.ychong.library.listener.OnRightListener
import com.ychong.library.utils.ResUtils


/**
 * @author ychong
 * date: 2020/5/27
 * desc:
 */
class InputDialog : Dialog, View.OnClickListener {
    private var binding: DialogInputBinding? = null
    private var onRightListener: OnRightListener? = null
    private var onLeftListener: OnLeftListener? = null
    private var title: String? = null
    private var titleColor: Int? = null
    private var titleSize: Float? = null
    private var textLen = 16
    private var msg: String? = null
    private var height: Int? = null
    private var hintText: String? = null
    private var marginSpan: Int = 100
    private var dimAmount: Float = 0.2f
    private var background: Int? = null
    private var leftBackground: Int? = null
    private var rightBackground: Int? = null

    fun setOnRightListener(onRightListener: OnRightListener?): InputDialog {
        this.onRightListener = onRightListener
        return this
    }

    fun setOnLeftListener(onLeftListener: OnLeftListener): InputDialog {
        this.onLeftListener = onLeftListener
        return this
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
        binding!!.leftTv.setOnClickListener(this)
        binding!!.rightTv.setOnClickListener(this)
        binding!!.textEt.onFocusChangeListener = View.OnFocusChangeListener { p0, p1 ->
            if (p1) {
                binding!!.clearTextIv.visibility = View.VISIBLE
            } else {
                binding!!.clearTextIv.visibility = View.INVISIBLE
            }
        }
        binding!!.clearTextIv.setOnClickListener(this)
    }

    fun setTitle(title: String?): InputDialog {
        if (title.isNullOrEmpty()) return this
        this.title = title
        return this
    }

    fun setTitleColor(titleColor: Int?): InputDialog {
        if (titleColor == null) return this
        this.titleColor = titleColor
        return this
    }

    fun setTitleSize(titleSize: Float?): InputDialog {
        if (titleSize == null) return this
        this.titleSize = titleSize
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

    fun setHintText(hintText: String?): InputDialog {
        if (hintText.isNullOrEmpty()) return this
        this.hintText = hintText
        return this
    }

    fun setDimAmount(dimAmount: Float?): InputDialog {
        if (dimAmount == null || dimAmount < 0 || dimAmount > 1) return this
        this.dimAmount = dimAmount
        return this
    }

    fun setBackground(background: Int?): InputDialog {
        if (background == null) return this
        this.background = background
        return this
    }

    fun setLeftBg(leftBackground: Int?): InputDialog {
        if (leftBackground == null) return this
        this.leftBackground = leftBackground
        return this
    }

    fun setRightBg(rightBackground: Int?): InputDialog {
        if (rightBackground == null) return this
        this.rightBackground = rightBackground
        return this
    }

    fun build(): InputDialog {
        show()
        setViewData()
        return this
    }

    private fun setViewData() {
        if (title.isNullOrEmpty()) {
            binding!!.titleTv.visibility = View.GONE
        } else {
            binding!!.titleTv.text = this.title
            binding!!.titleTv.setTextColor(ResUtils.getColor(context, this.titleColor!!))
            binding!!.titleTv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, this.titleSize!!)
        }
        window?.setDimAmount(this.dimAmount)
        binding!!.textEt.filters = arrayOf<InputFilter>(LengthFilter(textLen))
        binding!!.textEt.setText(msg)
        if (this.height != null) {
            binding!!.textEt.layoutParams.height = this.height!!
        }
        binding!!.textEt.hint = this.hintText
        if (background != null) {
            binding!!.inputLayout.background = ResUtils.getDrawable(context, this.background!!)
        }
        if (leftBackground != null) {
            binding!!.leftTv.background = ResUtils.getDrawable(context, this.leftBackground!!)
        }
        if (rightBackground != null) {
            binding!!.rightTv.background = ResUtils.getDrawable(context, this.rightBackground!!)
        }

    }

    fun getInput(): String? {
        return binding!!.textEt.text.toString()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.leftTv -> {
                if (onLeftListener != null) {
                    onLeftListener!!.left()
                }
                dismiss()
            }
            R.id.rightTv -> {
                if (onRightListener != null) {
                    onRightListener!!.right()
                }
                dismiss()
            }
            R.id.clearTextIv -> {
                binding!!.textEt.text = null
            }
        }
    }
}