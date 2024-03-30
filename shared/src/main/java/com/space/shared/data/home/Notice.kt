package com.space.shared.data.home

import kotlinx.serialization.Serializable

@Serializable
data class Notice(
    val title: String,
    val content: String
)
