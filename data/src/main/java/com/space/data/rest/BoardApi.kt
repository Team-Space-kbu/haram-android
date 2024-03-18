package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.board.BoardCategory
import com.space.shared.data.board.BoardDetail
import com.space.shared.data.board.BoardPage
import com.space.shared.data.board.Boards
import retrofit2.http.GET
import retrofit2.http.Path

interface BoardApi {
    @GET("/v1/board-categories")
    suspend fun getBoardCategory(): SpaceBody<List<BoardCategory>>

    @GET("/v1/board-categories/{categorySeq}/boards")
    suspend fun getBoardPage(
        @Path(value = "categorySeq") boardType: Int
    ): SpaceBody<BoardPage>

    @GET("/v1/board-categories/{categorySeq}/boards/{boardSeq}")
    suspend fun getBoardDetail(
        @Path(value = "boardSeq") boardSeq: Int,
        @Path(value = "categorySeq") categorySeq: Int
    ): SpaceBody<BoardDetail>
}