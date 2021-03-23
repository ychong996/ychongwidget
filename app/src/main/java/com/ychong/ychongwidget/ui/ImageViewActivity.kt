package com.ychong.ychongwidget.ui

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ychong.ychongwidget.R
import com.ychong.ychongwidget.databinding.ActivityImageviewBinding

class ImageViewActivity:AppCompatActivity() {
    private var binding:ActivityImageviewBinding? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_imageview)
    }
}