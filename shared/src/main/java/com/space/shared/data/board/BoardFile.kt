package com.space.shared.data.board

import kotlinx.serialization.Serializable

@Serializable
data class BoardFile(
    val fileSeq: Int,
    val boardSeq: Int,
    val path: String,
    val sortNum: Int
)
