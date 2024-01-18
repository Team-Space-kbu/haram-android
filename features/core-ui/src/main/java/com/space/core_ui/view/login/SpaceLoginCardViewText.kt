package com.space.core_ui.view.login

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.space.core_ui.R


class SpaceLoginCardViewText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {
    init {
        textSize = 14.5f
        setTextColor(context.getColor(R.color.white))
        setTypeface(null, Typeface.BOLD)
    }
}