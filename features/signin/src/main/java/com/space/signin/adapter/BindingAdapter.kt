package com.space.signin.adapter

import android.view.KeyEvent
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.space.signin.ui.login.LoginStatus
import com.space.core_ui.NonParamsItemHandler

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

