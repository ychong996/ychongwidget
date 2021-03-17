package com.ychong.library

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Matrix
import android.graphics.RectF
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.*
import androidx.appcompat.widget.AppCompatImageView
import kotlin.math.sqrt


class YCHZoomImageView : AppCompatImageView, ScaleGestureDetector.OnScaleGestureListener,
    View.OnTouchListener, ViewTreeObserver.OnGlobalLayoutListener {

    companion object {
        val TAG = YCHZoomImageView::class.simpleName
        const val SCALE_MAX = 4.0f
        const val SCALE_MID = 2.0f

        const val BIGGER = 1.07f
        const val SMALLER = 0.93f
    }

    /**初始化时的缩放比例，如果图片宽或者高大于屏幕，此值将小于0*/
    private var initScale: Float = 1.0f

    private var once: Boolean = true

    /**缩放的手势检测*/
    private var mScaleGestureDetector: ScaleGestureDetector? = null
    val mScaleMatrix: Matrix = Matrix()

    /**用于存放矩阵的9个值*/
    val matrixValues: FloatArray = FloatArray(9)

    /**双击手势检测*/
    private var mGestureDetector: GestureDetector? = null

    private var lastPointerCount: Int = 0
    private var isCanDrag: Boolean = true
    private var mLastX: Float = 0f
    private var mLastY: Float = 0f
    private var mTouchSlop: Int = 0
    private var isCheckLeftAndRight: Boolean = false
    private var isCheckTopAndBottom: Boolean = false

    private var isAutoScale: Boolean = false

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init()
    }

    override fun onScale(detector: ScaleGestureDetector?): Boolean {
        val scale = getScale()
        var scaleFactor = detector!!.scaleFactor
        if (drawable == null) {
            return true
        }
        //缩放的范围控制
        if ((scale < SCALE_MAX && scaleFactor > 1.0f) || (scale > initScale && scaleFactor < 1.0f)) {
            //最大值 最小值判断
            if (scaleFactor * scale < initScale) {
                scaleFactor = initScale / scale
            }
            if (scaleFactor * scale > SCALE_MAX) {
                scaleFactor = SCALE_MAX / scale
            }
            //设置缩放比例
            mScaleMatrix.postScale(scaleFactor, scaleFactor, detector.focusX, detector.focusX)
            checkBorderAndCenterWhenScale()
            imageMatrix = mScaleMatrix
        }
        return true
    }

    override fun onScaleBegin(detector: ScaleGestureDetector?): Boolean {
        return true
    }

    override fun onScaleEnd(detector: ScaleGestureDetector?) {
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        //拿到触摸点的个数
        val pointerCount = event!!.pointerCount
        if (pointerCount>=2){
            if (mScaleGestureDetector!!.onTouchEvent(event)){
                return true
            }
        }
        var x = 0f
        var y = 0f
        //得到多个触摸点的x与y均值
        for (index in 0 until pointerCount) {
            x += event.getX(index)
            y += event.getY(index)
        }
        x /= pointerCount
        y /= pointerCount

        //每当触摸点发生变化时，重置mLastX mLastY
        if (pointerCount != lastPointerCount) {
            isCanDrag = false
            mLastX = x
            mLastY = y
        }
        lastPointerCount = pointerCount

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
            }
            MotionEvent.ACTION_MOVE -> {
                var dx = x - mLastX
                var dy = y - mLastY

                if (!isCanDrag) {
                    isCanDrag = isCanDrag(dx, dy)
                }
                if (isCanDrag) {
                    val rectF = getMatrixRectF()
                    if (drawable != null) {
                        isCheckLeftAndRight = true
                        isCheckTopAndBottom = true
                        //如果宽度小于屏幕宽度，则禁止左右移动
                        if (rectF.width() < width) {
                            dx = 0f
                            isCheckLeftAndRight = false
                        }
                        //如果高度小于屏幕高度，则禁止上下移动
                        if (rectF.height()<height){
                            dy = 0f
                            isCheckTopAndBottom = false
                        }
                        mScaleMatrix.postTranslate(dx, dy)
                        checkMatrixBounds()
                        imageMatrix = mScaleMatrix
                    }
                }
                mLastX = x
                mLastY = y
            }
            MotionEvent.ACTION_UP,MotionEvent.ACTION_CANCEL -> {
                lastPointerCount = 0
            }
        }
        return true
    }

    override fun onGlobalLayout() {
        if (once) {
            val d: Drawable = drawable ?: return
            val width = width
            val height = height
            //拿到图片的宽和高
            val dw = d.intrinsicWidth
            val dh = d.intrinsicHeight
            var scale = 1.0f
            //如果图片的宽或者高大于屏幕，则缩放至屏幕的宽或者高
            if (dw > width && dh <= height) {
                scale = width * 1.0f / dw
            }
            if (dh > height && dw <= width) {
                scale = height * 1.0f / dh
            }
            //如果宽和高都大于屏幕，则让其按比例适应屏幕大小
            if (dw > width && dh > height) {
                scale = 0.5f // (dw * 1.0f / width).coerceAtMost(dh * 1.0f / height)
            }
            initScale = scale
            //图片移动到屏幕中心
            mScaleMatrix.postTranslate((width - dw) / 2f, (height - dh) / 2f)
            mScaleMatrix.postScale(scale, scale, width / 2f, height / 2f)
            imageMatrix = mScaleMatrix
            once = false
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        viewTreeObserver.addOnGlobalLayoutListener(this)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewTreeObserver.removeGlobalOnLayoutListener(this)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun init() {
        super.setScaleType(ScaleType.MATRIX)
        mScaleGestureDetector = ScaleGestureDetector(context, this)
        mGestureDetector = GestureDetector(context, object :
            GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent?): Boolean {
                if (isAutoScale) {
                    return true
                }
                val x = e!!.x
                val y = e.y
                isAutoScale = if (getScale() < SCALE_MID) {
                    this@YCHZoomImageView.postDelayed(AutoScaleRunnable(SCALE_MID, x, y), 16)
                    true
                } else if (getScale() >= SCALE_MID && getScale() < SCALE_MAX) {
                    this@YCHZoomImageView.postDelayed(AutoScaleRunnable(SCALE_MAX, x, y), 16)
                    true
                } else {
                    this@YCHZoomImageView.postDelayed(AutoScaleRunnable(initScale, x, y), 16)
                    true
                }
                return true
            }

        })
        mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
        super.setScaleType(ScaleType.MATRIX)
        this.setOnTouchListener(this)
    }

    /**在缩放时，进行图片显示范围的控制*/
    private fun checkBorderAndCenterWhenScale() {
        val rect = getMatrixRectF()
        var deltaX = 0f
        var deltaY = 0f

        val width = width
        val height = height

        //如果宽或者高大于屏幕 ，则控制范围
        if (rect.width() >= width) {
            if (rect.left > 0) {
                deltaX = -rect.left
            }
            if (rect.right < width) {
                deltaX = width - rect.right
            }
        }
        if (rect.height() >= height) {
            if (rect.top > 0) {
                deltaY = -rect.top
            }
            if (rect.bottom < height) {
                deltaY = height - rect.bottom
            }
        }
        //如果宽或者高小于屏幕，则让其居中
        if (rect.width() < width) {
            deltaX = width * 0.5f - rect.right + 0.5f * rect.width()
        }
        if (rect.height() < height) {
            deltaY = height * 0.5f - rect.bottom + 0.5f * rect.height()
        }
        mScaleMatrix.postTranslate(deltaX, deltaY)

    }

    /**获取当前的缩放比例*/
    fun getScale(): Float {
        mScaleMatrix.getValues(matrixValues)
        return matrixValues!![Matrix.MSCALE_X]
    }

    /**根据当前图片的Matrix获取图片的范围*/
    private fun getMatrixRectF(): RectF {
        val matrix = mScaleMatrix
        val rect = RectF()
        val d = drawable
        if (null != d) {
            rect.set(0f, 0f, d.intrinsicWidth * 1.0f, d.intrinsicHeight * 1.0f)
            matrix.mapRect(rect)
        }
        return rect
    }

    /**移动时，进行边界判断，主要判断宽或高大于屏幕*/
    private fun checkMatrixBounds() {
        val rect = getMatrixRectF()
        var deltaX = 0f
        var deltaY = 0f
        val viewWidth = width
        val viewHeight = height
        //判断移动或缩放后，图片显示是否超出屏幕边界
        if (rect.top > 0 && isCheckTopAndBottom) {
            deltaY = -rect.top
        }
        if (rect.bottom < viewHeight && isCheckTopAndBottom) {
            deltaY = viewHeight - rect.bottom
        }
        if (rect.left > 0 && isCheckLeftAndRight) {
            deltaX = -rect.left
        }
        if (rect.right < viewWidth && isCheckLeftAndRight) {
            deltaX = viewWidth - rect.right
        }
        mScaleMatrix.postTranslate(deltaX, deltaY)
    }

    /**是否是推动行为*/
    private fun isCanDrag(dx: Float, dy: Float): Boolean {
        return sqrt(((dx * dx) + (dy * dy)).toDouble()) >= mTouchSlop
    }

    /**自动缩放的任务*/
    inner class AutoScaleRunnable(
        private var tmpScale: Float,
        //缩放的中心
        private val x: Float, val y: Float
    ) :
        Runnable {
        override fun run() {
            // 进行缩放
            mScaleMatrix.postScale(tmpScale, tmpScale, x, y)
            checkBorderAndCenterWhenScale()
            imageMatrix = mScaleMatrix
            val currentScale: Float = getScale()
            //如果值在合法范围内，继续缩放
            if (tmpScale > 1f && currentScale < tmpScale
                || tmpScale < 1f && tmpScale < currentScale
            ) {
                this@YCHZoomImageView.postDelayed(this, 16)
            } else {//设置为目标的缩放比例
                val deltaScale = tmpScale / currentScale
                mScaleMatrix.postScale(deltaScale, deltaScale, x, y)
                checkBorderAndCenterWhenScale()
                imageMatrix = mScaleMatrix
                isAutoScale = false
            }
        }

        /**
         * 传入目标缩放值，根据目标值与当前值，判断应该放大还是缩小
         *
         * @param targetScale
         */
        init {
            tmpScale = if (getScale() < tmpScale) {
                BIGGER
            } else {
                SMALLER
            }
        }
    }
}