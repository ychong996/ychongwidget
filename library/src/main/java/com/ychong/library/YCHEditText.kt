package com.ychong.library

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText

class YCHEditText: AppCompatEditText,View.OnFocusChangeListener,TextWatcher {
    constructor(context:Context):super(context){
        init()
    }
    constructor(context: Context,attributeSet: AttributeSet):super(context,attributeSet){
        init()
    }
    constructor(context: Context,attributeSet: AttributeSet,defStyle:Int):super(context,attributeSet,android.R.attr.editTextStyle){
        init()
    }
    private fun init(){

    }

    override fun onTextChanged(
        text: CharSequence?,
        start: Int,
        lengthBefore: Int,
        lengthAfter: Int
    ) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter)
    }
    override fun onFocusChange(v: View?, hasFocus: Boolean) {
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun afterTextChanged(s: Editable?) {
    }
}