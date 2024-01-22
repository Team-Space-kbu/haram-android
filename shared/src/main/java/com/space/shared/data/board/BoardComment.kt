package com.space.shared.data.board

import kotlinx.serialization.Serializable

@Serializable
data class BoardComment(
    val commentSeq: String,
    val boardSeq: String,
    val userId: String,
    val commentContent: String,
    val createdAt: String,
    val modifiedAt: String
)
