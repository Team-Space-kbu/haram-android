package com.space.repository.service.board

import com.space.repository.api.BoardApi
import com.space.shared.data.board.BoardCategory
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class BoardServiceImpl @Inject constructor(
    private val boardApi: BoardApi
) : BoardService {
    override suspend fun getCategory(): List<BoardCategory> {
        return runBlocking {
            boardApi.getBoardCategory().data
        }
    }
}