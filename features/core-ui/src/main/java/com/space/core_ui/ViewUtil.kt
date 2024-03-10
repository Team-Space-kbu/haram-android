package com.space.core_ui

import android.content.Context
import android.util.TypedValue
import android.widget.Toast

fun Context.dpToPx(dp: Float): Float {
    val displayMetrics = resources.displayMetrics
    return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, displayMetrics)
}

@Suppress("DEPRECATION")
fun Context.spToPx(sp: Float): Float {
    val scaledDensity = resources.displayMetrics.scaledDensity
    return sp * scaledDensity
}

fun Context.showToast(text: String) {
    Toast.makeText(this, text, Toast.LENGTH_LONG).show()
}