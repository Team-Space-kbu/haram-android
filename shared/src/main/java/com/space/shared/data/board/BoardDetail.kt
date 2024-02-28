package com.space.shared.data.board

import kotlinx.serialization.Serializable

@Serializable
data class BoardDetail(
    val boardSeq: Int,
    val title: String,
    val contents: String,
    val createdBy: String,
    val createdAt: String,
    val isUpdatable: Boolean,
    val files: List<BoardFile>,
    val comments: List<BoardComment>
)
