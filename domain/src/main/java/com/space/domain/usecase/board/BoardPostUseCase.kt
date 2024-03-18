package com.space.domain.usecase.board

import com.space.domain.UseCase
import com.space.data.service.board.BoardService
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.model.BoardModel
import com.space.shared.model.ImageModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class BoardPostUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val boardService: BoardService
) : UseCase<Pair<Int, BoardModel>, Boolean>(dispatcher) {
    override suspend fun execute(param: Pair<Int, BoardModel>): Boolean {
        return boardService.postBoard(param.first, param.second).data
    }
}