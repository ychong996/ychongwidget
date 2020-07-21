package com.ychong.ychongwidget.dialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ychong.library.InputDialog
import com.ychong.ychongwidget.databinding.ActivityInputDialogBinding
import com.ychong.ychongwidget.utils.ToastUtils

class InputDialogActivity: AppCompatActivity(){
    private lateinit var binding: ActivityInputDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.inputTv.setOnClickListener{
            val dialog = InputDialog(this)
                .setTitle("标题")
                .setHintText("请输入内容")
                .setDimAmount(0.2f)
                .build()
            dialog.setOnClickListener(object : InputDialog.OnClickListener{
                override fun cancel() {
                    ToastUtils.showText(this@InputDialogActivity,"取消了")
                }

                override fun confirm(remark: String?) {
                    ToastUtils.showText(this@InputDialogActivity,remark)
                }

            })
        }
    }
}