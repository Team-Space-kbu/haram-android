package com.space.core_ui.view

import android.content.Context
import android.util.AttributeSet
import androidx.cardview.widget.CardView
import com.space.core_ui.R
import com.space.core_ui.dpToPx

class SpaceGreenButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : CardView(context, attrs) {
    init {
        setCardBackgroundColor(context.getColor(R.color.greenBackground))
        radius = context.dpToPx(10f)
        elevation = context.dpToPx(0f)
        minimumHeight = context.dpToPx(48f).toInt()
    }
}