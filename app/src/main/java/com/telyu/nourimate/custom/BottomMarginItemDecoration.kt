package com.telyu.nourimate.custom

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BottomMarginItemDecoration(private val bottomMargin: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        if (position == state.itemCount - 1) {
            // Tambahkan margin bawah hanya untuk item terakhir
            outRect.bottom = bottomMargin
        }
    }
}