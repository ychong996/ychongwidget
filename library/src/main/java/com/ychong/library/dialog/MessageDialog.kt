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
    private var onRightListener:OnRightListener? = null
    private var onLeftListener:OnLeftListener? = null
    private var titleColor: Int = R.color.color_333333
    private var titleStyle: Int? = Typeface.BOLD
    private var title: String? = null
    private var msg:String? = null
    private var msgColor:Int = R.color.color_666666
    private var msgSize:Float? = null
    private var msgStyle:Int? = Typeface.NORMAL

    fun setOnRightListener(onRightListener: OnRightListener):MessageDialog{
        this.onRightListener = onRightListener
        return this
    }
    fun setOnLeftListener(onLeftListener: OnLeftListener):MessageDialog{
        this.onLeftListener = onLeftListener
        return this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogMessageBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        binding!!.cancelTv.setOnClickListener(this)
        binding!!.confirmTv.setOnClickListener(this)
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
    fun setMsgSize(msgSize:Float?):MessageDialog{
        if (msgSize==null)return this
        this.msgSize = msgSize
        return this
    }
    fun setMsgStyle(msgStyle:Int?): MessageDialog {
        this.msgStyle = msgStyle
        return this
    }

    private fun initViewData() {
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
    }

    override fun onClick(v: View) {
        val id = v.id
        if (id == R.id.cancelTv) {
            if (onLeftListener!=null){
                onLeftListener!!.left()
            }

            dismiss()
        } else if (id == R.id.confirmTv) {
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