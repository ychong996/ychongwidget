package com.ychong.ychongwidget.dialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ychong.library.dialog.ToastDialog
import com.ychong.ychongwidget.databinding.ActivityToastDialogBinding

class ToastDialogActivity : AppCompatActivity(){
    private lateinit var binding: ActivityToastDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToastDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toastTv.setOnClickListener{
            val dialog = ToastDialog(this)
                .setMsg("吐司出来啦")
                .build()
        }
    }
}