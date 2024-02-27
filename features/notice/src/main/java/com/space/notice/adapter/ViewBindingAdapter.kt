package com.space.notice.adapter

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.space.shared.util.formatToDate


@SuppressLint("SetTextI18n")
@BindingAdapter("setDate", "setName")
fun setDetailDate(
    textView: TextView,
    date: String,
    name: String
) {
    textView.text = "${formatToDate(date)} | $name"
}