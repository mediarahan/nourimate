package com.telyu.nourimate.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.telyu.nourimate.R

class WeightChartView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        strokeWidth = 5f
        style = Paint.Style.FILL
    }
    private val strokePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color45)
        strokeWidth = 8f
        style = Paint.Style.STROKE
    }
    private val fillPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        textSize = 22f
    }
    private val datePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color44)
        textSize = 30f
        textAlign = Paint.Align.CENTER
    }
    private val lineBluePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color45)
        strokeWidth = 8f
        style = Paint.Style.STROKE
    }
    private val textBackgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color45)
        style = Paint.Style.FILL
    }

    private val path = Path()
    private val horizontalPadding = 50f
    private val verticalPadding = 50f  // Added vertical padding

    private var weights = listOf<Int>()
    private var dates = listOf<String>()

    // Method to update weights
    fun setWeights(newWeights: List<Int>) {
        weights = newWeights
        invalidate()  // Redraw the view to reflect the change
    }

    // Method to update dates
    fun setDates(newDates: List<String>) {
        dates = newDates
        invalidate()  // Redraw the view to reflect the change
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        fillPaint.shader = LinearGradient(0f, height.toFloat(), 0f, 0f,
            Color.parseColor("#FFE8A3"), Color.parseColor("#B4AF88"), Shader.TileMode.CLAMP)

        // Check if there are weights to draw
        if (weights.isNotEmpty()) {
            // First, draw the lines and prepare the path.
            prepareAndDrawPath(canvas)

            // After path is drawn, draw the interactive elements.
            drawInteractiveElements(canvas)
        }
    }


    private fun prepareAndDrawPath(canvas: Canvas) {
        if (weights.isEmpty()) return  // Early return if no weights

        val maxWeight = weights.maxOrNull() ?: return  // Return if weights are null or empty
        val minWeight = weights.minOrNull() ?: return
        val deltaX = (width - 2 * horizontalPadding) / (weights.size - 1)
        val deltaY = (height - 2 * verticalPadding - 60).toFloat() / (maxWeight - minWeight)

        path.reset()
        path.moveTo(horizontalPadding, height.toFloat() - verticalPadding - 20)

        weights.indices.forEach { i ->
            val x = i * deltaX + horizontalPadding
            val y = height - (weights[i] - minWeight) * deltaY - verticalPadding - 40
            path.lineTo(x, y)
        }

        path.lineTo(width - horizontalPadding, height.toFloat() - verticalPadding - 20)
        path.close()

        canvas.drawPath(path, fillPaint)
    }

    private fun drawInteractiveElements(canvas: Canvas) {
        if (weights.isEmpty()) return  // Early return if no weights

        val maxWeight = weights.maxOrNull() ?: return
        val minWeight = weights.minOrNull() ?: return
        val deltaX = (width - 2 * horizontalPadding) / (weights.size - 1).coerceAtLeast(1) // Avoid division by zero
        val deltaY = if (maxWeight == minWeight) 1f else (height - 2 * verticalPadding - 60) / (maxWeight - minWeight)

        if (weights.size == 1) {
            // Draw a single point if there is only one weight
            val x = horizontalPadding
            val y = height - (weights[0] - minWeight) * deltaY - verticalPadding - 40
            canvas.drawCircle(x, y, 15f, strokePaint)
            canvas.drawCircle(x, y, 10f, linePaint)
            canvas.drawRoundRect(x - 50, y - 50, x + 50, y - 10, 20f, 20f, textBackgroundPaint)
            canvas.drawText("${weights[0]}kg", x - 25f, y - 20f, textPaint)
            canvas.drawText(dates[0], x, height.toFloat() - verticalPadding + 5f, datePaint)
            return
        }

        var lastX = horizontalPadding
        var lastY = height - (weights[0] - minWeight) * deltaY - verticalPadding - 40

        for (i in 1 until weights.size) {
            val x = i * deltaX + horizontalPadding
            val y = height - (weights[i] - minWeight) * deltaY - verticalPadding - 40
            canvas.drawLine(lastX, lastY, x, y, lineBluePaint)
            lastX = x
            lastY = y
        }

        for (i in weights.indices) {
            val x = i * deltaX + horizontalPadding
            val y = height - (weights[i] - minWeight) * deltaY - verticalPadding - 40
            canvas.drawCircle(x, y, 15f, strokePaint)
            canvas.drawCircle(x, y, 10f, linePaint)
            canvas.drawRoundRect(x - 50, y - 50, x + 50, y - 10, 20f, 20f, textBackgroundPaint)
            canvas.drawText("${weights[i]}kg", x - 25f, y - 20f, textPaint)
            canvas.drawText(dates[i], x, height.toFloat() - verticalPadding + 5f, datePaint)
        }
    }






}
