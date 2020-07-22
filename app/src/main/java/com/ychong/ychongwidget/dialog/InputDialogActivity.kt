package com.ychong.ychongwidget.dialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ychong.library.dialog.InputDialog
import com.ychong.library.listener.OnLeftListener
import com.ychong.library.listener.OnRightListener
import com.ychong.ychongwidget.R
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
                .setTitleColor(R.color.color_666666)
                .setTitleSize(20f)
                .setHintText("请输入内容")
                .setDimAmount(0.2f)
                .build()
            dialog.setOnLeftListener(object : OnLeftListener{
                override fun left() {
                    ToastUtils.showText(this@InputDialogActivity,"左边")
                }

            }).setOnRightListener(object : OnRightListener{
                override fun right() {
                    ToastUtils.showText(this@InputDialogActivity,"右边")
                }

            })
        }
    }
}