package com.space.auth.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.space.core_ui.R


class SpaceHeaderTitle @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {
    init {
        textSize = 22f
        setTextColor(context.getColor(R.color.blackTitle))
        setTypeface(null, Typeface.BOLD)
    }
}