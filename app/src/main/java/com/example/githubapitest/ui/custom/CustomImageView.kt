package com.example.githubapitest.ui.custom

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.AttributeSet
import androidx.annotation.DrawableRes
import com.example.githubapitest.R
import kotlin.math.min

class CustomImageView : androidx.appcompat.widget.AppCompatImageView {
    private val drawableRect = RectF()
    private val borderRect = RectF()
    private val bitmapPaint: Paint? = Paint()
    private val borderPaint = Paint()
    private val fillPaint = Paint()
    private var borderColor = STANDARD_BORDER_COLOR
    private var borderWidth = STANDARD_BORDER_WIDTH


    private var ready = false
    private var setupPending = false
    private var borderOverlay = false
    private var bitmapProperties: Bitmap? = null
    private var bitmapShader: BitmapShader? = null
    private var bitmapWidth = 0
    private var bitmapHeight = 0
    private var drawableRadius = 0f
    private var borderRadius = 0f
    private var colorFilter: ColorFilter? = null
    private var disableCircularTransformation = false

    constructor(context: Context) : super(context) {
        init()
    }

    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyle: Int = 0
    ) : super(context, attrs, defStyle) {
        val attributes =
            context.obtainStyledAttributes(attrs, R.styleable.CustomImageView, defStyle, 0)
        borderWidth = attributes.getDimensionPixelSize(
            R.styleable.CustomImageView_border_width,
            STANDARD_BORDER_WIDTH
        )
        borderColor = attributes.getColor(
            R.styleable.CustomImageView_border_color,
            STANDARD_BORDER_COLOR
        )
        borderOverlay = attributes.getBoolean(
            R.styleable.CustomImageView_border_overlay,
            STANDARD_BORDER_OVERLAY
        )

        attributes.recycle()
        init()
    }

    private fun init() {
        super.setScaleType(SCALE)
        ready = true
        if (setupPending) {
            setupPending = false
            config()
        }
    }

    override fun getScaleType(): ScaleType {
        return SCALE
    }

    override fun setScaleType(scaleType: ScaleType) {
        require(scaleType == SCALE) {
            String.format(
                "ScaleType %s not supported.",
                scaleType
            )
        }
    }

    override fun setAdjustViewBounds(adjustViewBounds: Boolean) {
        require(!adjustViewBounds) { "adjustViewBounds not supported." }
    }

    override fun onDraw(canvas: Canvas) {
        if (disableCircularTransformation) {
            super.onDraw(canvas)
            return
        }
        if (bitmapProperties == null) {
            return
        }

        bitmapPaint?.let {
            canvas.drawCircle(
                drawableRect.centerX(),
                drawableRect.centerY(),
                drawableRadius,
                it
            )
        }
        if (borderWidth > 0) {
            canvas.drawCircle(
                borderRect.centerX(),
                borderRect.centerY(),
                borderRadius,
                borderPaint
            )
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        config()
    }

    override fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        super.setPadding(left, top, right, bottom)
        config()
    }

    override fun setPaddingRelative(
        start: Int,
        top: Int,
        end: Int,
        bottom: Int
    ) {
        super.setPaddingRelative(start, top, end, bottom)
        config()
    }


    override fun setImageBitmap(bm: Bitmap) {
        super.setImageBitmap(bm)
        initializeBitmap()
    }

    override fun setImageDrawable(drawable: Drawable?) {
        super.setImageDrawable(drawable)
        initializeBitmap()
    }

    override fun setImageResource(@DrawableRes resId: Int) {
        super.setImageResource(resId)
        initializeBitmap()
    }

    override fun setImageURI(uri: Uri?) {
        super.setImageURI(uri)
        initializeBitmap()
    }

    override fun setColorFilter(cf: ColorFilter) {
        if (cf === colorFilter) {
            return
        }
        colorFilter = cf
        invalidate()
    }

    override fun getColorFilter(): ColorFilter? {
        return colorFilter
    }

    private fun getBitmapFromDrawable(drawable: Drawable?): Bitmap? {
        if (drawable == null) {
            return null
        }
        return if (drawable is BitmapDrawable) {
            drawable.bitmap
        } else try {
            val bitmap: Bitmap = if (drawable is ColorDrawable) {
                Bitmap.createBitmap(
                    DRAWABLE_DIMENSION_COLOR,
                    DRAWABLE_DIMENSION_COLOR,
                    CONFIG
                )
            } else {
                Bitmap.createBitmap(
                    drawable.intrinsicWidth,
                    drawable.intrinsicHeight,
                    CONFIG
                )
            }
            val canvas = Canvas(bitmap)
            drawable.setBounds(0, 0, canvas.width, canvas.height)
            drawable.draw(canvas)
            bitmap
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun initializeBitmap() {
        bitmapProperties = if (disableCircularTransformation) {
            null
        } else {
            getBitmapFromDrawable(drawable)
        }
        config()
    }

    private fun config() {
        if (!ready) {
            setupPending = true
            return
        }
        if (width == 0 && height == 0) {
            return
        }
        if (bitmapProperties == null) {
            invalidate()
            return
        }
        if (bitmapProperties != null) {
            bitmapShader = BitmapShader(
                bitmapProperties as Bitmap,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
        } else {
            invalidate()
            return
        }

        bitmapPaint?.isAntiAlias = true
        bitmapPaint?.shader = bitmapShader
        borderPaint.style = Paint.Style.STROKE
        borderPaint.isAntiAlias = true
        borderPaint.color = borderColor
        borderPaint.strokeWidth = borderWidth.toFloat()
        fillPaint.style = Paint.Style.FILL
        fillPaint.isAntiAlias = true
        bitmapHeight = bitmapProperties?.height ?: 0
        bitmapWidth = bitmapProperties?.width ?: 0
        borderRect.set(calcBounds())
        borderRadius = min(
            (borderRect.height() - borderWidth) / 2.0f,
            (borderRect.width() - borderWidth) / 2.0f
        )
        drawableRect.set(borderRect)
        if (!borderOverlay && borderWidth > 0) {
            drawableRect.inset(borderWidth - 1.0f, borderWidth - 1.0f)
        }
        drawableRadius =
            min(drawableRect.height() / 2.0f, drawableRect.width() / 2.0f)
        invalidate()
    }

    private fun calcBounds(): RectF {
        val availableWidth = width - paddingLeft - paddingRight
        val availableHeight = height - paddingTop - paddingBottom
        val sideLength = min(availableWidth, availableHeight)
        val left = paddingLeft + (availableWidth - sideLength) / 2f
        val top = paddingTop + (availableHeight - sideLength) / 2f
        return RectF(left, top, left + sideLength, top + sideLength)
    }


    companion object {
        private val SCALE = ScaleType.CENTER_CROP
        private const val STANDARD_BORDER_WIDTH = 0
        private const val STANDARD_BORDER_COLOR = Color.BLACK
        private const val STANDARD_BORDER_OVERLAY = false
        private val CONFIG = Bitmap.Config.ARGB_8888
        private const val DRAWABLE_DIMENSION_COLOR = 2

    }
}
