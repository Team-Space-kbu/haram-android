package com.space.shared.data.board

import kotlinx.serialization.Serializable

@Serializable
data class BoardComment(
    val seq: String,
    val contents: String,
    val createdBy: String,
    val createdAt: String,
    val isUpdatable: Boolean
)
