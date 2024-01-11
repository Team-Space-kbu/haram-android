package com.space.auth.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.space.core_ui.R


class SpaceCardViewText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {
    init {
        textSize = 14.5f
        setTextColor(context.getColor(R.color.white))
        setTypeface(null, Typeface.BOLD)
    }
}