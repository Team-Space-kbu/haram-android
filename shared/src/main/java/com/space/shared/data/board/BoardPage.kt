package com.space.shared.data.board

import com.space.shared.data.Category
import kotlinx.serialization.Serializable

@Serializable
data class BoardPage(
    val boardSeq: Int,
    val boardTitle: String,
    val userId: String,
    val boardContent: String,
) {
    companion object {
        fun toCategory(boardPage: BoardPage): Category {
            return Category(
                boardPage.boardTitle,
                boardPage.boardContent,
                boardPage.boardSeq.toString()
            )
        }
    }
}
