package com.space.domain.board

import com.space.domain.UseCase
import com.space.data.service.board.BoardService
import com.space.space_annotation.annotation.IoDispatcher
import com.space.shared.data.board.BoardComment
import com.space.shared.data.board.BoardDetailNum
import com.space.shared.model.BoardCommentModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class BoardCommentUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val boardService: BoardService
) : UseCase<Pair<BoardDetailNum, BoardCommentModel>, List<BoardComment>>(dispatcher) {
    override suspend fun execute(param: Pair<BoardDetailNum, BoardCommentModel>): List<BoardComment> {
        return boardService.setComment(param.first, param.second).data
    }
}