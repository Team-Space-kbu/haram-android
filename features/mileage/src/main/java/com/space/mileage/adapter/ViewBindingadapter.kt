package com.space.mileage.adapter

import android.graphics.Color
import android.util.Log
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("mileageType")
fun layoutType(
    textView: TextView,
    string: String
) {
    if (string.contains("조정")) {
        textView.setTextColor(
            Color.parseColor("#4B81EE")
        )
    }
}
