package com.space.auth.view

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.card.MaterialCardView
import com.space.core_ui.R
import com.space.core_ui.dpToPx
import com.space.core_ui.spToPx

class SpaceEditView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : MaterialCardView(context, attrs) {

    init {
        setCardBackgroundColor(context.getColor(R.color.boxBackground))
        strokeColor = context.getColor(R.color.boxStrokeColor)
        strokeWidth = context.spToPx(1f).toInt()
        elevation = context.dpToPx(0f)
        radius = context.dpToPx(10f)
        layoutParams = LayoutParams(
            LayoutParams.MATCH_PARENT,
            context.dpToPx(45f).toInt()
        )
    }


}