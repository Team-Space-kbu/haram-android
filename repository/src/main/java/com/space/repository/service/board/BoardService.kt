package com.space.repository.service.board

import com.space.shared.SpaceBody
import com.space.shared.data.board.BoardCategory
import com.space.shared.data.board.BoardPage

interface BoardService {
    suspend fun getCategory(): List<BoardCategory>

    suspend fun getPage(type: String): SpaceBody<List<BoardPage>>
}