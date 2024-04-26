package com.telyu.nourimate

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Color
import android.util.AttributeSet
import android.view.View

class CustomRunningGraphView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {
    private val linePaint = Paint().apply {
        color = Color.BLUE // Warna garis
        strokeWidth = 8f // Ketebalan garis
        isAntiAlias = true
    }

    private var speedData = listOf<Int>() // Data sederhana untuk contoh grafik lari

    fun setData(data: List<Int>) {
        this.speedData = data
        invalidate() // Memanggil onDraw lagi dengan data baru
        requestLayout() // Jika ukuran View bisa berubah, panggil ini juga
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Pastikan kita memiliki setidaknya 2 data poin untuk menggambar garis
        if (speedData.size < 2) return

        // Hitung skala vertikal dan horizontal
        val maxSpeed = speedData.maxOrNull() ?: return
        val minSpeed = speedData.minOrNull() ?: return
        val verticalScale = height / (maxSpeed - minSpeed).toFloat()
        val horizontalScale = width / (speedData.size - 1).toFloat()

        // Gambar grafik lari
        for (i in 0 until speedData.size - 1) {
            val startX = i * horizontalScale
            val startY = height - (speedData[i] - minSpeed) * verticalScale
            val stopX = (i + 1) * horizontalScale
            val stopY = height - (speedData[i + 1] - minSpeed) * verticalScale
            canvas.drawLine(startX, startY, stopX, stopY, linePaint)
        }
    }
}
