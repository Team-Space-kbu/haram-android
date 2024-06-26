package com.space.domain.board

import com.space.domain.NonParamUseCase
import com.space.data.service.board.BoardService
import com.space.shared.annotation.IoDispatcher
import com.space.shared.data.board.BoardCategory
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class BoardCategoryUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val boardService: BoardService
) : NonParamUseCase<List<BoardCategory>>(dispatcher) {
    override suspend fun execute(): List<BoardCategory> {
        return boardService.getCategory()
    }
}