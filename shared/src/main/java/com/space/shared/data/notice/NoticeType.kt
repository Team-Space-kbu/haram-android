package com.space.shared.data.notice

import kotlinx.serialization.Serializable

@Serializable
data class NoticeType(
    val key: String,
    val tag: String
)
