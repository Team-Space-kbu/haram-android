package com.space.core_ui.binding.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.extension.dpToPixel

class FlexGrayLineDecoration(
    context: Context,
    resId: Int,
    private val space: Int, // 아이템 간의 간격
) : RecyclerView.ItemDecoration() {

    private val padding by lazy {
        context.dpToPixel(15F).toInt()
    }

    private var mDivider: Drawable? = null

    init {
        mDivider = ContextCompat.getDrawable(context, resId)
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
    }

    override fun onDraw(
        canvas: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        for (i in 0 until parent.childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams

            // 위쪽 마진 설정
            params.topMargin = space
            // 아래쪽 마진 설정
            params.bottomMargin = space

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