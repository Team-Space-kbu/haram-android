package com.space.shared.data.core_ui

data class PolicyForm<T>(
    val policies: T,
    val title: String,
    val content: String
)
