package com.space.domain.usecase.board

import com.space.domain.base.UseCase
import com.space.data.service.board.BoardService
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.board.BoardPage
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class BoardPageUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val boardService: BoardService
) : UseCase<String, List<BoardPage>>(dispatcher) {
    override suspend fun execute(param: String): List<BoardPage> {
        val board = boardService.getPage(param)
        board.let {
            if (board.code == "BA01") {
                throw NullPointerException("The data does not exist.")
            }
        }
        return board.data
    }
}