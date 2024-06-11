package com.space.domain.board

import com.space.domain.UseCase
import com.space.data.service.board.BoardService
import com.space.space_annotation.annotation.IoDispatcher
import com.space.shared.data.board.BoardDetail
import com.space.shared.data.board.BoardDetailNum
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class BoardDetailUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val boardService: BoardService
) : UseCase<BoardDetailNum, BoardDetail>(dispatcher) {
    override suspend fun execute(param: BoardDetailNum): BoardDetail {
        return boardService.getDetail(param).data
    }
}