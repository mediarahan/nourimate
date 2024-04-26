package com.telyu.nourimate.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.animation.ValueAnimator

class SemiCircleProgressView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var progress: Float = 0f // Current progress
    private val maxProgress = 3000f // Maximum progress as shown in the image

    private val paint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 65f // Change this to modify the stroke width
        strokeCap = Paint.Cap.ROUND // Rounded stroke ends
        color = Color.parseColor("#E99800") // Adjust the color to match the progress color in the image
    }

    private val backgroundPaint = Paint().apply {
        isAntiAlias = true
        style = Paint.Style.STROKE
        strokeWidth = 60f // Match the stroke width of the progress
        strokeCap = Paint.Cap.ROUND // Rounded stroke ends
        color = Color.parseColor("#F8D484") // Light orange background color
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val strokeWidthOffset = paint.strokeWidth / 2
        val ovalTop = strokeWidthOffset
        val ovalBottom = height.toFloat() - strokeWidthOffset

        // Defines the rectangle for the full circle, with adjustments for the stroke width.
        val oval = RectF(
            strokeWidthOffset, // left
            ovalTop, // top
            width.toFloat() - strokeWidthOffset, // right
            ovalBottom // bottom
        )

        // Draw the background semi-circle for the full arc
        canvas.drawArc(oval, 168f, 203f, false, backgroundPaint)

        // Calculate the angle for the current progress
        val progressAngle = 203f * (progress / maxProgress)
        // Draw the progress semi-circle for the current progress
        canvas.drawArc(oval, 168f, progressAngle, false, paint)
    }







    fun setProgress(newProgress: Float) {
        this.progress = newProgress.coerceIn(0f, maxProgress)
        invalidate() // Redraw the view
    }

    fun animateProgress(toProgress: Float, duration: Long = 2000) { // Match the duration to the one seen in the image or as needed
        ValueAnimator.ofFloat(progress, toProgress).apply {
            this.duration = duration
            interpolator = AccelerateDecelerateInterpolator()
            addUpdateListener { animation ->
                setProgress(animation.animatedValue as Float)
            }
            start()
        }
    }
}
