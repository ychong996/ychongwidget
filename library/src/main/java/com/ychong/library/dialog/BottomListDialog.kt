package com.ychong.library.dialog

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import com.ychong.library.R

class BottomListDialog(context:Context) : Dialog(context){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_bottom_list)
    }
}