package com.space.auth.adapter

import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter

@BindingAdapter("onLoginListener")
fun onLoginListener(
    view: CardView,
    event: OnClickLoginButton
) {
    view.setOnClickListener {
        event.onClick()
    }
    view.setOnKeyListener { _, keyCode, keyEvent ->
        if (keyCode == KeyEvent.KEYCODE_ENTER && keyEvent.action == KeyEvent.ACTION_UP) {
            event.onClick()
        }
        false
    }
}

@BindingAdapter("inputStatus")
fun setLoginStatus(textView: TextView, boolean: Boolean) {
    textView.visibility = if (!boolean) View.GONE else View.VISIBLE
}

