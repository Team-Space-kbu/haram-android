package com.space.repository.api

import com.space.shared.SpaceBody
import com.space.shared.data.board.BoardCategory
import retrofit2.http.GET

interface BoardApi {
    @GET("/v1/boards/categories")
    suspend fun getBoardCategory(): SpaceBody<List<BoardCategory>>


}