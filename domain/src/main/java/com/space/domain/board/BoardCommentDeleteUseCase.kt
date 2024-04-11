package com.space.domain.board

import com.space.domain.UseCase
import com.space.data.service.board.BoardService
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.board.BoardDetailNum
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class BoardCommentDeleteUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val boardService: BoardService
) : UseCase<Pair<BoardDetailNum, Int>, Boolean>(dispatcher) {
    override suspend fun execute(param: Pair<BoardDetailNum, Int>): Boolean {
        return boardService.deleteComment(param.first, param.second).data
    }
}