package com.space.core_ui

import androidx.appcompat.app.AppCompatActivity

inline fun <reified T : Any> AppCompatActivity.extra(
    key: String,
    default: T? = null
) = lazy {
    val value = intent.extras?.get(key)
    if (value is String) value else default
}

inline fun <reified T : Any> AppCompatActivity.extraNotNull(
    key: String,
    default: T? = null
) = lazy {
    val value = intent.extras?.get(key)
    requireNotNull(if (value is T) value else default) { key }
}