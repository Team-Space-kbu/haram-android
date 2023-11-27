package com.space.shared.model

data class ViewListenerModel(
    val viewPath: Int? = null,
    val viewStatus: Boolean = false,
    val keyEvent: Boolean = false,
)