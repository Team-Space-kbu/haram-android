package com.space.auth.adapter

import android.content.Context
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.space.auth.ui.login.LoginStatus
import com.space.core_ui.NonParamsItemHandler
import timber.log.Timber

@BindingAdapter("onLoginListener")
fun onLoginListener(
    view: CardView,
    event: NonParamsItemHandler
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
fun setLoginStatus(textView: TextView, loginStatus: LoginStatus) {
    textView.visibility = when (loginStatus) {
        LoginStatus.Success -> View.GONE
        LoginStatus.EMPTY -> View.GONE
        LoginStatus.FAIL -> View.VISIBLE
    }
}

