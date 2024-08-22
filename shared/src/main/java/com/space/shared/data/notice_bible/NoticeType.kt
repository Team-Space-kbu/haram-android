package com.space.shared.data.notice_bible

import kotlinx.serialization.Serializable

@Serializable
data class NoticeType(
    val key: String,
    val tag: String
)
