package com.ychong.library.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import com.ychong.library.R
import com.ychong.library.databinding.DialogMessageBinding
import com.ychong.library.listener.OnLeftListener
import com.ychong.library.listener.OnRightListener
import com.ychong.library.utils.ResUtils

/**
 * @author ychong
 * date: 2020/5/27
 * desc:
 */
class MessageDialog(context: Context) : Dialog(context), View.OnClickListener {
    private var binding: DialogMessageBinding? = null
    private var onRightListener: OnRightListener? = null
    private var onLeftListener: OnLeftListener? = null
    private var titleColor: Int = R.color.color_333333
    private var titleStyle: Int? = Typeface.BOLD
    private var title: String? = null
    private var msg:String? = null
    private var msgColor:Int = R.color.color_666666
    private var msgSize:Float? = null
    private var msgStyle:Int? = Typeface.NORMAL
    private var isCan:Boolean = false
    private var dimAmount:Float = 0.3f

    private var leftText:String = "取消"
    private var rightText:String = "确认"

    private var leftTextColor:Int = R.color.color_666666
    private var leftTextSize:Float = 16f
    private var leftTextStyle:Int = Typeface.NORMAL

    private var rightTextColor:Int = R.color.color_666666
    private var rightTextSize:Float = 16f
    private var rightTextStyle:Int = Typeface.NORMAL

    fun setOnRightListener(onRightListener: OnRightListener): MessageDialog {
        this.onRightListener = onRightListener
        return this
    }
    fun setOnLeftListener(onLeftListener: OnLeftListener): MessageDialog {
        this.onLeftListener = onLeftListener
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogMessageBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.leftTv.setOnClickListener(this)
        binding!!.rightTv.setOnClickListener(this)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        window?.setWindowAnimations(R.style.alpha_center_animation)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
    }

    fun setTitle(title: String?): MessageDialog {
        this.title = title
        return this
    }
    fun setTitleStyle(titleStyle:Int?) : MessageDialog {
        this.titleStyle = titleStyle
        return this
    }

    fun setMsg(msg:String?) : MessageDialog {
        this.msg = msg
        return this
    }
    fun setMsgSize(msgSize:Float?): MessageDialog {
        if (msgSize==null)return this
        this.msgSize = msgSize
        return this
    }
    fun setMsgColor(msgColor:Int):MessageDialog{
        this.msgColor = msgColor
        return this
    }
    fun setMsgStyle(msgStyle:Int?): MessageDialog {
        this.msgStyle = msgStyle
        return this
    }

    fun setLeftText(leftText:String):MessageDialog{
        this.leftText = leftText
        return this
    }
    fun setLeftTextStyle(leftTextStyle:Int):MessageDialog{
        this.leftTextStyle = leftTextStyle
        return this
    }
    fun setLeftTextColor(leftTextColor:Int):MessageDialog{
        this.leftTextColor = leftTextColor
        return this
    }
    fun setLeftTextSize(leftTextSize:Float):MessageDialog{
        this.leftTextSize = leftTextSize
        return this
    }
    fun setRightText(rightText:String):MessageDialog{
        this.rightText = rightText
        return this
    }
    fun setRightTextStyle(rightTextStyle:Int):MessageDialog{
        this.rightTextStyle = rightTextStyle
        return this
    }
    fun setRightTextColor(rightTextColor:Int):MessageDialog{
        this.rightTextColor = rightTextColor
        return this
    }
    fun setRightTextSize(rightTextSize:Float):MessageDialog{
        this.rightTextSize = rightTextSize
        return this
    }


    fun setIsCan(isCan:Boolean): MessageDialog {
        this.isCan = isCan
        return this
    }
    fun setDimAmount(dimAmount:Float?): MessageDialog {
        if (dimAmount==null||dimAmount<0||dimAmount>1)return this
        this.dimAmount = dimAmount
        return this
    }

    private fun initViewData() {
        setCancelable(isCan)
        setCanceledOnTouchOutside(isCan)
        window?.setDimAmount(this.dimAmount)
        //标题
        if (title.isNullOrEmpty()) {
            binding!!.titleTv.visibility = View.GONE
        } else {
            binding!!.titleTv.text = this.title
            binding!!.titleTv.setTextColor(ResUtils.getColor(context, titleColor))
            if (titleStyle!=null){
                binding!!.titleTv.setTypeface(Typeface.DEFAULT, titleStyle!!)
            }
        }
        binding!!.msgTv.text = this.msg
        if (msgSize!=null){
            binding!!.msgTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,msgSize!!)
        }
        //文本
        binding!!.msgTv.setTextColor(ResUtils.getColor(context,msgColor))
        if (msgStyle!=null){
            binding!!.msgTv.setTypeface(Typeface.DEFAULT,msgStyle!!)
        }

        binding!!.leftTv.text = leftText
        binding!!.leftTv.setTextColor(ResUtils.getColor(context,leftTextColor))
        binding!!.leftTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,leftTextSize)
        binding!!.leftTv.setTypeface(Typeface.DEFAULT,leftTextStyle)

        binding!!.rightTv.text = rightText
        binding!!.rightTv.setTextColor(ResUtils.getColor(context,rightTextColor))
        binding!!.rightTv.setTextSize(TypedValue.COMPLEX_UNIT_SP,rightTextSize)
        binding!!.rightTv.setTypeface(Typeface.DEFAULT,rightTextStyle)
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.leftTv) {
            if (onLeftListener!=null){
                onLeftListener!!.left()
            }

            dismiss()
        } else if (id == R.id.rightTv) {
            if (onRightListener!=null){
                onRightListener!!.right()
            }
            dismiss()
        }
    }

    fun build() : MessageDialog {
        show()
        initViewData()
        return this
    }


}