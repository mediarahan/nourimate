package com.telyu.nourimate.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import android.graphics.Typeface
import com.telyu.nourimate.R
import kotlin.math.round
import kotlin.math.abs

class StraightRulerView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    interface OnValueChangeListener {
        fun onValueChanged(value: Float)
    }

    var selectedValue = 0f
        set(value) {
            field = value.coerceIn(0f, maxRulerValue)
            updateRulerOffsetFromSelectedValue()
            listener?.onValueChanged(field)
        }

    var listener: OnValueChangeListener? = null

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = ContextCompat.getColor(context, R.color.color38)
        strokeWidth = 3f
        style = Paint.Style.STROKE
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 15 * resources.displayMetrics.scaledDensity // Mengubah ukuran teks menjadi 30sp
        textAlign = Paint.Align.CENTER
        typeface = ResourcesCompat.getFont(context, R.font.zenkakugothicnewregular) // Menggunakan font Roboto
    }

    private val indicatorPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#F7B400")
        strokeWidth = 8f
        style = Paint.Style.FILL
    }

    private val maxRulerValue = 200f
    private var rulerOffset = 0f
    private var lastX = 0f

    private val gapBetweenNumbers = 25f // Distance between main tick marks
    private val lengthOfMainTick = 70f // Length of main tick marks
    private val lengthOfSmallTick = 40f // Length of small tick marks

    private fun updateRulerOffsetFromSelectedValue() {
        rulerOffset = -selectedValue * gapBetweenNumbers
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val rulerStart = width / 2f + rulerOffset
        val mainTickInterval = 10
        val topMargin = 20f
        val lengthOfMainTick = 100f
        val lengthOfMidTick = 70f
        val lengthOfSmallTick = 40f

        val highestTick = lengthOfMainTick // garis terpanjang yang ada

        for (i in 0..200) {
            val xPosition = rulerStart + i * gapBetweenNumbers
            val isMainTick = i % mainTickInterval == 0
            val lineLength = when (i % 10) {
                0 -> lengthOfMainTick
                5 -> lengthOfMidTick
                else -> lengthOfSmallTick
            }

            // Semua garis dimulai dari tingkat yang sama, berbeda panjang ke bawah
            canvas.drawLine(xPosition, topMargin, xPosition, topMargin + lineLength, paint)

            // Menambahkan teks hanya untuk kelipatan 10
            if (isMainTick) {
                val textY = topMargin + lengthOfMainTick + 20 + textPaint.textSize
                canvas.drawText(i.toString(), xPosition, textY, textPaint)
            }
        }

        // Menggambar indikator
        val middleX = width / 2f
        val middleY = topMargin + highestTick / 2f
        val indicatorLength = highestTick + 20f  // membuat indikator 20px lebih panjang dari garis terpanjang

        val indicatorTopY = middleY - indicatorLength / 2
        val indicatorBottomY = middleY + indicatorLength / 2

        val indicatorPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#F7B400")  // Warna oranye
            strokeWidth = 9f  // Ketebalan garis penunjuk
        }

        canvas.drawLine(middleX, indicatorTopY, middleX, indicatorBottomY, indicatorPaint)
    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        val currentX = event.x

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastX = currentX
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                val deltaX = currentX - lastX
                if (abs(deltaX) > 2) { // Adding a dead zone to avoid jitter
                    rulerOffset += deltaX
                    updateSelectedValueFromOffset()
                    lastX = currentX
                    invalidate()
                }
                return true
            }
            MotionEvent.ACTION_UP -> {
                selectedValue = round(selectedValue)
                listener?.onValueChanged(selectedValue)
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun updateSelectedValueFromOffset() {
        selectedValue = -rulerOffset / gapBetweenNumbers
        selectedValue = selectedValue.coerceIn(0f, maxRulerValue)
    }
}
