package com.space.repository.service.board

import com.space.shared.data.board.BoardCategory

interface BoardService {
    suspend fun getCategory(): List<BoardCategory>
}