package com.space.core_ui

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

enum class EditType {
    TEXT,
    PHONE,
    NAME,
    ID,
    PASSWORD,
    NUMBER
}

fun Context.hideKeyboard( editText: EditText) {
    val inputMethodManager =
        getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(editText.windowToken, 0)
}