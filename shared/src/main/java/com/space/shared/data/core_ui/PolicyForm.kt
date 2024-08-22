package com.space.shared.data.core_ui

import android.text.Spanned

data class PolicyForm<T>(
    val policies: T,
    val title: String,
    val content: Spanned
)
