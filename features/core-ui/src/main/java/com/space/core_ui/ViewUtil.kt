package com.space.core_ui

import android.content.Context

fun Context.dpToPx(dp: Float): Float {
    val density = resources.displayMetrics.density
    return dp * density
}

fun Context.spToPx(sp: Float): Float {
    val scaledDensity = resources.displayMetrics.scaledDensity
    return sp * scaledDensity
}