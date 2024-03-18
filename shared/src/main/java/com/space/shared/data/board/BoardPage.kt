package com.space.shared.data.board

data class BoardPage(
    val categorySeq: Int,
    val categoryName: String,
    val writeableBoard: Boolean,
    val writeableAnonymous: Boolean,
    val startPage: Int,
    val endPage: Int,
    val boards: List<Boards>
)
