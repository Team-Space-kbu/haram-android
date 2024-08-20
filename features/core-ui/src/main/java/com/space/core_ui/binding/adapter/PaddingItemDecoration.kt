package com.space.core_ui.binding.adapter

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.space.core_ui.extension.dpToPixel

class PaddingItemDecoration(
    context: Context,
    private val margin: Int,
    private val setPadding: Boolean = true
) : RecyclerView.ItemDecoration() {

    private val padding by lazy {
        if (setPadding) {
            context.dpToPixel(15F).toInt()
        }else{
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
        outRect.top = margin / 2
        outRect.bottom = margin / 2
        outRect.left = padding
        outRect.right = padding
    }


}