package com.ychong.ychongwidget.dialog

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ychong.library.LoadingDialog
import com.ychong.ychongwidget.R
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
        binding.cancleTv.setOnClickListener{
            hideLoading()
        }
    }


    private var dialog: LoadingDialog? = null
    private fun showLoading() {
        dialog = LoadingDialog(this)
        dialog!!.setText("正在加载中")
            .setLoadingImg(R.mipmap.ic_loading)
            .setTextColor(R.color.color_0D71DF)
            .setBackgroundColor(R.color.color_red)
            .setBackground(R.drawable.bg_333333_side_radius)
            .isCan(true)
            .build()
    }

    private fun hideLoading() {
        if (dialog!=null){
            dialog!!.dismiss()
        }

    }

}