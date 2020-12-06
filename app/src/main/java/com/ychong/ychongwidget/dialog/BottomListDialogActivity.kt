package com.ychong.ychongwidget.dialog

import android.app.Activity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.ychong.library.dialog.BottomListDialog
import com.ychong.ychongwidget.R
import com.ychong.ychongwidget.databinding.ActivityBottomListBinding

class BottomListDialogActivity : AppCompatActivity(), View.OnClickListener {
    private var binding:ActivityBottomListBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_list)

        binding!!.dialogBtn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.dialogBtn ->{
                showDialog()
            }
        }
    }

    private fun showDialog(){
        val list = ArrayList<String>()
        list.add("相机")
        list.add("相册")

        BottomListDialog(this)
            .setData(list)
            .build()
    }
}