package com.ychong.ychongwidget.dialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ychong.library.OnClickListener
import com.ychong.library.TipsDialog
import com.ychong.ychongwidget.databinding.ActivityTipsDialogBinding
import com.ychong.ychongwidget.utils.ToastUtils

class TipsDialogActivity : AppCompatActivity(){
    private lateinit var binding: ActivityTipsDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipsDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tipsTv.setOnClickListener{
            val dialog = TipsDialog(this)
                .setMsg("提示成功了")
                .build()
            dialog.setTipsListener(object : OnClickListener{
                override fun click() {
                    ToastUtils.showText(this@TipsDialogActivity,"知道了")
                }

            })
        }
    }
}