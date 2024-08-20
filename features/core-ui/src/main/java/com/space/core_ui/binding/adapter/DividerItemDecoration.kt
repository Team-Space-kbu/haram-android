package com.space.core_ui.binding.adapter

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.extension.dpToPixel

@Deprecated("사용하지 않음")
class DividerItemDecoration(
    context: Context,
    resId: Int,
    private val paddingLeft: Int,
    private val paddingRight: Int,
    private val endIndex: Int = 1,
    private val specificIndex: Int = -1,
) : RecyclerView.ItemDecoration() {

    private var mDivider: Drawable? = null


    init {
        mDivider = ContextCompat.getDrawable(context, resId)
    }

    override fun onDrawOver(
        canvas: Canvas,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val itemCount = parent.adapter?.itemCount ?: 0

        for (i in 0 until parent.childCount - endIndex) {
            // 마지막 줄 제외한 아이템에 대해서만 그리기 작업 수행
            if (i != itemCount - 1 && i != specificIndex) {
                val child = parent.getChildAt(i)
                val params = child.layoutParams as RecyclerView.LayoutParams
                val left = parent.paddingLeft + paddingLeft
                val right = parent.width - parent.paddingRight - paddingRight
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