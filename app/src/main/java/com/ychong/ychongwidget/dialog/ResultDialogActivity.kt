package com.ychong.ychongwidget.dialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ychong.library.ResultDialog
import com.ychong.ychongwidget.R
import com.ychong.ychongwidget.databinding.ActivityResultDialogBinding

class ResultDialogActivity : AppCompatActivity(){
    private lateinit var binding: ActivityResultDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.resultTv.setOnClickListener{
            val dialog = ResultDialog(this)
                .setResultImg(R.mipmap.ic_success_tag)
                .setTips("结果成功了")
                .build()
        }
    }
}