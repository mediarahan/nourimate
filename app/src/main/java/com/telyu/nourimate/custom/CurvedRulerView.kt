package com.telyu.nourimate.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import android.graphics.*
import android.view.MotionEvent
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.max
import kotlin.math.min
import kotlin.math.atan2
import kotlin.math.PI

class CurvedRulerView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

    interface OnValueChangeListener {
        fun onValueChanged(value: Float)
    }

    var listener: OnValueChangeListener? = null


    private val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.DKGRAY
        strokeWidth = 8f
        style = Paint.Style.STROKE
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 40f
        textAlign = Paint.Align.CENTER
    }

    private val indicatorPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.YELLOW
        strokeWidth = 10f
        style = Paint.Style.FILL
    }

    private var selectedValue = 0f // Nilai awal
    private var radius: Float = 0f // Set an initial value


    private val maxRulerValue = 150f
    private val tickInterval = maxRulerValue / 20  // For 20 total tick marks.

    private val valueToAngleConverter = { value: Float ->
        320f - (320* (value / maxRulerValue))
    }



    private val angleToPositionConverter = { angle: Float, customRadius: Float? ->
        val r = customRadius ?: radius
        val angleInRadians = Math.toRadians(angle.toDouble()).toFloat()
        Pair(width / 2f + r * cos(angleInRadians), height - r * sin(angleInRadians))
    }





    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Variabel untuk mengatur panjang garis utama dan garis penanda
        val mainTickLength = 70  // Panjang garis utama yang memiliki angka
        val secondaryTickLength = 40  // Panjang garis penanda tanpa angka
        val textOffset = 20  // Jarak antara akhir garis dan teks angka

        for (i in 0..150) {
            val angle = valueToAngleConverter(i.toFloat()) - offsetAngle
            val (startX, startY) = angleToPositionConverter(angle, null)

            val isMainLine = i % 10 == 0
            val endRadius = if (isMainLine) radius - mainTickLength else radius - secondaryTickLength
            val (endX, endY) = angleToPositionConverter(angle, endRadius)

            canvas.drawLine(startX, startY, endX, endY, paint)

            // Tambahkan teks hanya pada garis utama
            if (isMainLine) {
                val text = i.toString()
                val textRadius = endRadius - textOffset  // adjust textOffset if needed
                val (_, textBaseY) = angleToPositionConverter(angle, textRadius)
                // Adjust the text position downwards by adding a factor to textBaseY
                val textY = textBaseY + textPaint.textSize / 1  // Half of text size to move it down a bit

                canvas.drawText(text, startX, textY, textPaint)
            }
        }

        // Draw the indicator (static in the middle)
        val indicatorPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.parseColor("#FFA500") // Warna oranye
            strokeWidth = 8f // Ketebalan garis penunjuk
        }

        val middleX = width / 2f
        val indicatorTopY = height - radius // Atas garis penunjuk
        val indicatorBottomY = height.toFloat() // Bawah garis penunjuk

        canvas.drawLine(middleX, indicatorTopY, middleX, indicatorBottomY, indicatorPaint)

        // Draw the indicator (static at the center)
        val (indicatorX, indicatorY) = angleToPositionConverter(90f - offsetAngle, null)
        canvas.drawCircle(indicatorX, indicatorY, 20f, indicatorPaint)
    }



    private var offsetAngle = 0f  // Sudut pergeseran, nilai awal 0

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
            val deltaX = event.x - width / 2
            // Penyesuaian untuk sensitivitas geseran, angka lebih kecil membuat pergerakan lebih lambat
            offsetAngle += (deltaX / width * 180) / 10 // Misalnya, bagi dengan 10 untuk mengurangi kecepatan geser

            // Batasi offsetAngle agar angka 0 dan 150 hanya bisa mentok di tengah
            val maxOffsetAngle = valueToAngleConverter(0f) - 90f  // Angka 0 di tengah
            val minOffsetAngle = valueToAngleConverter(150f) - 90f  // Angka 150 di tengah

            listener?.onValueChanged(selectedValue)

            offsetAngle = max(minOffsetAngle, min(maxOffsetAngle, offsetAngle))

            // Ketika menghitung offsetAngle, hitung juga nilai terpilih
            selectedValue = (maxRulerValue * (90f - offsetAngle) / 320f).coerceIn(0f, maxRulerValue)
            listener?.onValueChanged(selectedValue)

            invalidate()  // Minta penggambaran ulang
            return true
        }
        return super.onTouchEvent(event)
    }







    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // Increase the radius to zoom in the view
        radius = w / 2f * 2 // Menyesuaikan faktor pengali untuk zoom
    }


}



