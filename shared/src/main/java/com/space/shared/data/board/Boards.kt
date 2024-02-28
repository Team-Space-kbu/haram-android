package com.space.shared.data.board

import com.space.shared.data.Category
import kotlinx.serialization.Serializable

@Serializable
data class Boards(
    val boardSeq: Int,
    val title: String,
    val contents: String,
    val createdBy: String,
) {
    companion object {
        fun toCategory(boards: Boards): Category {
            return Category(
                boards.title,
                boards.contents,
                boards.boardSeq.toString()
            )
        }
    }
}
