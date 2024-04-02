package com.space.data.service.board

import com.space.shared.SpaceBody
import com.space.shared.data.board.BoardCategory
import com.space.shared.data.board.BoardComment
import com.space.shared.data.board.BoardDetail
import com.space.shared.data.board.BoardDetailNum
import com.space.shared.data.board.BoardPage
import com.space.shared.model.BoardCommentModel
import com.space.shared.model.BoardModel

interface BoardService {
    suspend fun getCategory(): List<BoardCategory>

    suspend fun getPage(type: Int, page: Int): SpaceBody<BoardPage>

    suspend fun postBoard(
        type: Int,
        boardModel: BoardModel
    ): SpaceBody<Boolean>

    suspend fun deleteBoard(
        type: Int,
        seq: Int
    ): SpaceBody<Boolean>

    suspend fun getDetail(type: BoardDetailNum): SpaceBody<BoardDetail>

    suspend fun setComment(
        type: BoardDetailNum,
        comment: BoardCommentModel
    ): SpaceBody<List<BoardComment>>

    suspend fun deleteComment(
        type: BoardDetailNum,
        seq: Int
    ): SpaceBody<Boolean>
}