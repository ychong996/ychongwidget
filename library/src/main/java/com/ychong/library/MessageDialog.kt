package com.ychong.library

import android.app.Dialog
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import com.ychong.library.databinding.DialogMessageBinding
import com.ychong.library.utils.ResUtils

/**
 * @author ychong
 * date: 2020/5/27
 * desc:
 */
class MessageDialog(context: Context) : Dialog(context), View.OnClickListener {
    private var binding: DialogMessageBinding? = null
    private var messageListener: MessageListener? = null
    private var titleColor: Int = R.color.color_333333
    private var titleStyle: Int? = Typeface.BOLD
    private var title: String? = null
    private var msg:String? = null
    private var msgColor:Int = R.color.color_666666
    private var msgStyle:Int? = Typeface.NORMAL
    fun setMessageListener(messageListener: MessageListener?) {
        this.messageListener = messageListener
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
    fun setTitleStyle(titleStyle:Int?) : MessageDialog{
        this.titleStyle = titleStyle
        return this
    }

    fun setMsg(msg:String?) : MessageDialog{
        this.msg = msg
        return this
    }
    fun setMsgStyle(msgStyle:Int?):MessageDialog{
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

        //文本
        binding!!.msgTv.setTextColor(ResUtils.getColor(context,msgColor))
        if (msgStyle!=null){
            binding!!.msgTv.setTypeface(Typeface.DEFAULT,msgStyle!!)
        }
    }

    override fun onClick(v: View) {
        if (messageListener == null) {
            dismiss()
            return
        }
        val id = v.id
        if (id == R.id.cancelTv) {
            messageListener!!.cancel()
            dismiss()
        } else if (id == R.id.confirmTv) {
            messageListener!!.confirm()
            dismiss()
        }
    }

    fun build() :MessageDialog{
        show()
        initViewData()
        return this
    }

    interface MessageListener {
        fun cancel()
        fun confirm()
    }

}