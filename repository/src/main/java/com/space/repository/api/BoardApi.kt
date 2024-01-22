package com.space.repository.api

import com.space.shared.SpaceBody
import com.space.shared.data.board.BoardCategory
import com.space.shared.data.board.BoardDetail
import com.space.shared.data.board.BoardPage
import retrofit2.http.GET
import retrofit2.http.Path

interface BoardApi {
    @GET("/v1/boards/categories")
    suspend fun getBoardCategory(): SpaceBody<List<BoardCategory>>

    @GET("/v1/boards/{boardType}")
    suspend fun getBoardPage(
        @Path(value = "boardType") boardType: String
    ): SpaceBody<List<BoardPage>>

    @GET("/v1/boards/{boardType}/{boardSeq}")
    suspend fun getBoardDetail(
        @Path(value = "boardType") boardType: String,
        @Path(value = "boardSeq") boardSeq: String,
    ): SpaceBody<BoardDetail>
}