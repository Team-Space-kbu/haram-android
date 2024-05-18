package com.space.domain.board

import com.space.domain.UseCase
import com.space.data.service.board.BoardService
import com.space.builder_annotation.annotation.IoDispatcher
import com.space.shared.data.board.BoardDetailNum
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class BoardDetailDeleteUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val boardService: BoardService
) : UseCase<BoardDetailNum, Boolean>(dispatcher) {
    override suspend fun execute(param: BoardDetailNum): Boolean {
        return boardService.deleteBoard(param.categorySeq, param.boardSeq).data
    }
}