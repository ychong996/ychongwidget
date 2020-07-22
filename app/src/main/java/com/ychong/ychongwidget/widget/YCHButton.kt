package com.ychong.ychongwidget.widget

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.ColorStateListDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.StateListDrawable
import android.util.AttributeSet
import android.view.Gravity
import androidx.appcompat.widget.AppCompatButton
import com.ychong.library.utils.ResUtils
import com.ychong.ychongwidget.R

class YCHButton : AppCompatButton {
    private var normalColor: Int? = null
    private var pressedColor: Int? = null
    private var enabledColor: Int? = null
    private var normalTextColor:Int? = null
    private var pressedTextColor:Int? = null
    private var enabledTextColor:Int? = null
    private var radiusSize: Float? = null
    private var mGravity: Int? = null

    constructor(context: Context) : this(context,null) {

    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs,0) {

    }


    @SuppressLint("Recycle", "ResourceType")
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.YCHButton)
        normalColor = typedArray.getColor(
            R.styleable.YCHButton_normal_color,
            ResUtils.getColor(context, R.color.color_0D71DF)
        )
        pressedColor = typedArray.getColor(
            R.styleable.YCHButton_pressed_color,
            ResUtils.getColor(context, R.color.color_999999)
        )
        enabledColor = typedArray.getColor(
            R.styleable.YCHButton_enabled_color,
            ResUtils.getColor(context, R.color.color_999999)
        )
        normalTextColor = typedArray.getColor(
            R.styleable.YCHButton_normal_text_color,
            ResUtils.getColor(context,R.color.color_333333)
        )
        pressedTextColor = typedArray.getColor(
            R.styleable.YCHButton_pressed_text_color,
            ResUtils.getColor(context,R.color.color_666666)
        )
        enabledTextColor = typedArray.getColor(
            R.styleable.YCHButton_enabled_text_color,
            ResUtils.getColor(context,R.color.color_999999)
        )
        radiusSize = typedArray.getDimension(R.styleable.YCHButton_radius_size, 5f)
        mGravity = typedArray.getInt(R.styleable.YCHButton_android_gravity, Gravity.CENTER)
        typedArray.recycle()
        init()
    }

    private fun init() {
        gravity = mGravity!!
        setBackgroundDrawable(
            getStateListDrawable(
                getSolidRectDrawable(
                    radiusSize!!,
                    pressedColor!!
                ), getSolidRectDrawable(radiusSize!!, normalColor!!)
            )
        )
    }


    /**
     * 背景选择器
     */
    private fun getStateListDrawable(
        pressedDrawable: Drawable,
        normalDrawable: Drawable
    ): StateListDrawable {
        val stateListDrawable = StateListDrawable()
        val pressedArray = IntArray(2)
        pressedArray[0] = android.R.attr.state_enabled
        pressedArray[1] = android.R.attr.state_pressed
        stateListDrawable.addState(pressedArray, pressedDrawable)
        val enabledArray = IntArray(1)
        enabledArray[0] = android.R.attr.state_enabled
        stateListDrawable.addState(enabledArray, normalDrawable)

        //设置不能用的状态
        //默认其他状态的背景
        val gray = getSolidRectDrawable(radiusSize!!, enabledColor!!)
        stateListDrawable.addState(IntArray(0), gray)
        return stateListDrawable
    }

    /**
     * 得到实心的Drawable,一般作为选中，点中的效果
     */
    private fun getSolidRectDrawable(cornerRadius: Float, solidColor: Int): GradientDrawable {
        val gradientDrawable = GradientDrawable()
        //设置矩形的圆角半径
        gradientDrawable.cornerRadius = cornerRadius
        //设置绘画图片色值
        gradientDrawable.setColor(solidColor)
        //绘画的是矩形
        gradientDrawable.gradientType = GradientDrawable.RADIAL_GRADIENT
        return gradientDrawable
    }

}