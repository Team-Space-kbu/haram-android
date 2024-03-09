package com.space.core_ui.view

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import com.space.core_ui.R


class SpaceLoginHeaderText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatTextView(context, attrs) {
    init {
        textSize = 14f
        setTextColor(context.getColor(R.color.grayText))
        setTypeface(null, Typeface.NORMAL)
    }
}