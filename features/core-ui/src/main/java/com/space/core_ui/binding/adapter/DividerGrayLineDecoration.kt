package com.space.core_ui.binding.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.extension.dpToPixel

class DividerGrayLineDecoration(
    context: Context,
    resId: Int,
    private val margin: Int, // 아이템 간의 간격
    private val setPadding: Boolean = true
) : RecyclerView.ItemDecoration() {

    private var mDivider: Drawable? = null

    private val padding by lazy {
        if (setPadding) {
            context.dpToPixel(15F).toInt()
        } else {
            context.dpToPixel(0F).toInt()
        }
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.left = padding
        outRect.right = padding
        if (setPadding) {
            outRect.bottom = margin / 2
        }
    }

    init {
        mDivider = ContextCompat.getDrawable(context, resId)
    }

    override fun onDrawOver(
        canvas: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            params.topMargin = margin
            if (i != (parent.childCount - 1)) {
                params.bottomMargin = margin
                child.layoutParams = params
                val left = parent.paddingLeft
                val right = parent.width - parent.paddingRight
                val top = child.bottom + params.bottomMargin
                val bottom = top + (mDivider?.intrinsicHeight ?: 0)
                mDivider?.let {
                    it.setBounds(left, top, right, bottom)
                    it.draw(canvas)
                }
            }
        }
    }
}