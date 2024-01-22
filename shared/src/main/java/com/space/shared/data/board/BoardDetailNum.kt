package com.space.shared.data.board

import kotlinx.serialization.Serializable

@Serializable
data class BoardDetailNum(
    val boardSeq: String,
    val boardType: String
)
