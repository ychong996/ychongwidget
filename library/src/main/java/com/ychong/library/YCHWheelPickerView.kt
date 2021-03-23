package com.ychong.library

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.TextPaint
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import com.ychong.library.dialog.ToastDialog
import java.nio.channels.FileLock
import kotlin.math.abs

class YCHWheelPickerView : View {
    private val dataList: MutableList<String> = ArrayList()
    private var textPaint: TextPaint? = null

    private var centerX: Float = 0f
    private var centerY: Float = 0f

    //按下时屏幕的y坐标
    private var downY: Float = 0f

    private var textSize: Float = 0f
    private var textColor: Int = Color.BLACK
    private var textPadding: Float = 0f

    private var curTextColor: Int = Color.BLUE
    private var curBgPaint: Paint? = null
    private var curBgColor:Int = Color.WHITE

    private var linePaint: Paint? = null
    private var lineColor: Int = Color.BLACK
    private var lineWidth: Float = 0f

    //文本高度
    private var textHeight: Float = 0f

    //文本宽度
    private var textWidth: Float = 0f

    //当前选中的下标
    private var curIndex: Int = 0

    private var offsetIndex: Int = 0

    //本次滑动的y坐标偏移量
    private var offsetY: Float = 0f

    //是否处于滑动状态
    private var isSliding: Boolean = false

    private var textGravity: Int = TEXT_CENTER

    companion object {
        const val TEXT_LEFT: Int = 3
        const val TEXT_CENTER: Int = 17
        const val TEXT_RIGHT: Int = 5
        const val TEXT_TOP:Int = 48
        const val TEXT_BOTTOM:Int = 80
    }

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyle: Int) : super(
        context,
        attributeSet,
        defStyle
    ) {
        init(context, attributeSet, defStyle)
    }

    private fun init(context: Context, attributeSet: AttributeSet? = null, defStyle: Int = 0) {
        val style = context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.YCHWheelPickerView,
            defStyle,
            0
        )
        textSize = style.getDimensionPixelSize(
            R.styleable.YCHWheelPickerView_ychTextSize,
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 16f, resources.displayMetrics)
                .toInt()
        ).toFloat()
        textColor = style.getColor(R.styleable.YCHWheelPickerView_ychTextColor, Color.BLACK)
        curTextColor = style.getColor(R.styleable.YCHWheelPickerView_ychCurTextColor, Color.BLUE)
        curBgColor = style.getColor(R.styleable.YCHWheelPickerView_ychCurBgColor, Color.WHITE)
        lineColor = style.getColor(R.styleable.YCHWheelPickerView_ychLineColor, Color.WHITE)
        textPadding = style.getDimensionPixelSize(
            R.styleable.YCHWheelPickerView_ychTextPadding,
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, resources.displayMetrics)
                .toInt()
        ).toFloat()
        lineWidth = style.getDimensionPixelSize(
            R.styleable.YCHWheelPickerView_ychLineWidth,
            TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1f, resources.displayMetrics)
                .toInt()
        ).toFloat()
        textGravity = style.getInteger(R.styleable.YCHWheelPickerView_ychGravity, TEXT_CENTER)
        style.recycle()

        textPaint = TextPaint()
        textPaint!!.textSize = textSize
        textPaint!!.isAntiAlias = true
        val pfm: Paint.FontMetrics = textPaint!!.fontMetrics

        textHeight = pfm.bottom - pfm.top


        //当前选中的背景
        curBgPaint = Paint()
        curBgPaint!!.color = curBgColor

        //当前选中上下线
        linePaint = Paint()
        linePaint!!.color = lineColor

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val widthMode = MeasureSpec.getMode(widthMeasureSpec)
        var width = MeasureSpec.getSize(widthMeasureSpec)
        if (widthMode != MeasureSpec.EXACTLY) {
            //wrap_content
        }

        val heightMode = MeasureSpec.getMode(heightMeasureSpec)
        var height = MeasureSpec.getSize(heightMeasureSpec)
        if (heightMode != MeasureSpec.EXACTLY) {
            //wrap_content
        }

        centerX = width / 2.0f
        centerY = height / 2.0f

        setMeasuredDimension(width, height)
    }

    override fun onDraw(canvas: Canvas?) {
        val size: Int = dataList.size
        if (size > 0) {
            drawCurRect(canvas)
            //通过控件的高度和当个文本的高度来计算绘制多少
            val half: Int = height/(textHeight+textPadding).toInt()
            for (i in -half..half) {
                val index = curIndex - offsetIndex + i
                if (index in 0 until size) {
                    //计算每个字的中间y坐标
                        val centerPadding:Float = textHeight + textPadding
                    var tempY: Float = centerY + i * centerPadding
                    tempY += (offsetY % centerPadding)
                    //绘制
                    val text: String = dataList[index]
                    val tw: Float = textPaint!!.measureText(text)
                    val tempFm: Paint.FontMetrics = textPaint!!.fontMetrics
                    var x: Float = centerX - tw / 2
                    val y: Float = tempY - (tempFm.ascent + tempFm.descent) / 2
                    if (textGravity == TEXT_LEFT){
                        x = centerX-width/2+textPadding
                    }else if (textGravity== TEXT_RIGHT){
                        x = centerX+width/2-textPadding
                    }
                    if (i == 0) {
                        drawCurText(canvas, text, x, y)
                    } else {
                        drawOtherText(canvas, text, x, y)
                    }

                }
            }
        }
    }

    /**设置drawRect*/
    private fun drawCurRect(canvas: Canvas?) {
        val top:Float =centerY - textHeight/2-textPadding
        val bottom:Float = centerY + textHeight/2+textPadding
        //当前选中的背景
        canvas!!.drawRect(
            0f,
            top,
            width.toFloat(),
            bottom,
            curBgPaint!!
        )
        //上线
        canvas.drawRect(
            0f,
            top + lineWidth,
            width.toFloat(),
            top,
            linePaint!!
        )
        //下线
        canvas.drawRect(
            0f,
            bottom - lineWidth,
            width.toFloat(),
            bottom,
            linePaint!!
        )
    }

    /**绘制当前选中文本*/
    private fun drawCurText(canvas: Canvas?, text: String,  x: Float, y: Float) {
        //选中的文本
        textPaint!!.color = curTextColor
        canvas!!.drawText(
            text,
            x,
            y,
            textPaint!!
        )
    }

    /**绘制其他文本*/
    private fun drawOtherText(canvas: Canvas?, text: String,  x: Float, y: Float) {
        textPaint!!.color = textColor
        canvas!!.drawText(
            text,
            x,
            y,
            textPaint!!
        )
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event!!.action) {
            MotionEvent.ACTION_DOWN -> {
                downY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                //手指y坐标移动距离 正负
                offsetY = event.y - downY
                isSliding = true
                //计算偏移的个数
                val offSetNum: Int = (offsetY / (textHeight + textPadding)).toInt()
                val num = curIndex - offSetNum
                if (num >= 0 && num < dataList.size) {
                    offsetIndex = offSetNum
                    invalidate()
                }
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                isSliding = false
                val v: Float = offsetY % (textHeight + textPadding)
                if (v > 0.5f * (textHeight + textPadding)) {
                    ++offsetIndex
                } else if (v < -0.5f * (textHeight + textPadding)) {
                    --offsetIndex
                }
                curIndex -= offsetIndex
                if (curIndex <= 0) {
                    curIndex = 0
                } else if (curIndex >= this.dataList.size - 1) {
                    curIndex = this.dataList.size - 1
                }
                offsetIndex = 0
                offsetY = 0f
                postInvalidate()
                onScrollChangedListener?.onScrollChanged(curIndex)
            }

        }
        return true
    }

    fun setDataList(list: MutableList<String>) {
        this.dataList.clear()
        this.dataList.addAll(list)

        val size = this.dataList.size
        if (size > 0) {
            for (index in 0 until size) {
                val tempWidth: Float = textPaint!!.measureText(this.dataList[index])
                if (tempWidth > textWidth) {
                    textWidth = tempWidth
                }
            }
            curIndex = 0

            requestLayout()
            invalidate()
        }
    }

    fun setCur(index:Int){
        if (index>=0&&index<this.dataList.size){
            this.curIndex = index
            invalidate()
        }
    }

    private var onScrollChangedListener: OnScrollChangedListener? = null
    fun setScrollChangedListener(onScrollChangedListener: OnScrollChangedListener) {
        this.onScrollChangedListener = onScrollChangedListener
    }

    interface OnScrollChangedListener {
        fun onScrollChanged(curIndex: Int)
    }
}