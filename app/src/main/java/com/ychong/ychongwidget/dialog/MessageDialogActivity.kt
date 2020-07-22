package com.ychong.ychongwidget.dialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ychong.library.dialog.MessageDialog
import com.ychong.ychongwidget.databinding.ActivityMessageDialogBinding

class MessageDialogActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMessageDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.confirmTipsTv.setOnClickListener{
            val dialog = MessageDialog(this)
                .setMsg("您想清楚了吗？")
                .build()
        }
    }
}