package com.space.core_ui.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.setPadding
import com.space.core_ui.util.dpToPx

class SpaceLoginEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {

    init {
        setView()
    }

    private fun setView(){
        textSize = 14.5f
        setBackgroundColor(Color.TRANSPARENT)
        setSelectAllOnFocus(true)
        setPadding(context.dpToPx(10f).toInt())
    }


}