package com.space.shared.data.board

import kotlinx.serialization.Serializable

@Serializable
data class BoardFile(
    val fileUrl: String,
    val seq: Int,
    val sortNum: Int
)
