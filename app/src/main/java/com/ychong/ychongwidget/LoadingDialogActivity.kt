package com.ychong.ychongwidget

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ychong.library.LoadingDialog
import com.ychong.ychongwidget.databinding.ActivityLoadingDialogBinding

class LoadingDialogActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadingDialogBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadingDialogBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loadingTv.setOnClickListener {
            showLoading()
        }
        binding.loadingTv.setOnClickListener{
            hideLoading()
        }
    }


    private var dialog: LoadingDialog? = null
    private fun showLoading() {
        if (dialog == null) {
            dialog = LoadingDialog(this)
        }
        dialog!!.setText("正在加载中")
            .setBackgroundColor(R.color.colorAccent)
            .setDelayTime(5000)
            .setLoadingImg(R.mipmap.ic_launcher)
            .build()
    }

    private fun hideLoading() {
        if (dialog!=null){
            dialog!!.dismiss()
        }

    }

}