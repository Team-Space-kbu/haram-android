package com.space.signup.adapter

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData

@BindingAdapter("setStatus")
fun setViewStatus(
    textView: TextView,
    inputStatus: MutableLiveData<Boolean>
) {
    if (inputStatus.value == true) {
        textView.visibility = View.VISIBLE
    } else {
        textView.visibility = View.GONE
    }
}