package com.space.board.adapter

import android.annotation.SuppressLint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.space.shared.data.board.BoardDetail
import com.space.shared.util.Formatter.formatToDate


@BindingAdapter("setAnonymous")
fun setAnonymous(
    checkBox: CheckBox,
    isAnonymous: Boolean
) {
    checkBox.visibility = if (isAnonymous) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

@BindingAdapter("setUpdatable")
fun setUpdatable(
    textView: TextView,
    isUpdatable: Boolean
) {
    textView.visibility= if (isUpdatable) {
        View.VISIBLE
    } else {
        View.GONE
    }
}
