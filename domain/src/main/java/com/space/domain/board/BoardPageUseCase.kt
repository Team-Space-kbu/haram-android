package com.space.domain.board

import com.space.domain.UseCase
import com.space.data.service.board.BoardService
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.board.BoardPage
import com.space.shared.data.board.Boards
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class BoardPageUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val boardService: BoardService
) : UseCase<Pair<Int, Int>, BoardPage>(dispatcher) {
    override suspend fun execute(param: Pair<Int, Int>): BoardPage {
        val board = boardService.getPage(param.first, param.second)
        board.let {
            if (board.code == "BA01") {
                throw NullPointerException("The data does not exist.")
            }
        }
        return board.data
    }
}