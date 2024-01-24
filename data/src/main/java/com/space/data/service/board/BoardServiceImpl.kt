package com.space.data.service.board

import com.space.data.rest.BoardApi
import com.space.shared.SpaceBody
import com.space.shared.data.board.BoardCategory
import com.space.shared.data.board.BoardDetail
import com.space.shared.data.board.BoardDetailNum
import com.space.shared.data.board.BoardPage
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

    override suspend fun getPage(type: String): SpaceBody<List<BoardPage>> {
        return runBlocking {
            boardApi.getBoardPage(type)
        }
    }

    override suspend fun getDetail(type: BoardDetailNum): SpaceBody<BoardDetail> {
        return runBlocking {
            boardApi.getBoardDetail(type.boardType, type.boardSeq)
        }
    }
}