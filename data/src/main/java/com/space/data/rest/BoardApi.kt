package com.space.data.rest

import com.space.data.service.board.BoardCommentDeleteModel
import com.space.data.service.board.BoardDeleteModel
import com.space.shared.SpaceBody
import com.space.shared.data.board.BoardCategory
import com.space.shared.data.board.BoardComment
import com.space.shared.data.board.BoardDetail
import com.space.shared.data.board.BoardPage
import com.space.shared.model.BoardCommentModel
import com.space.shared.model.BoardModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface BoardApi {
    @GET("/v1/board-categories")
    suspend fun getBoardCategory(): SpaceBody<List<BoardCategory>>

    @GET("/v1/board-categories/{categorySeq}/boards")
    suspend fun getBoardPage(
        @Path(value = "categorySeq") boardType: Int,
        @Query("page") page: Int = 1
    ): SpaceBody<BoardPage>

    @POST("/v1/board-categories/{categorySeq}/boards")
    suspend fun postBoard(
        @Path(value = "categorySeq") boardType: Int,
        @Body boardModel: BoardModel
    ): SpaceBody<Boolean>

    @HTTP(
        method = "DELETE",
        path = "/v1/board-categories/{categorySeq}/boards",
        hasBody = true
    )
    suspend fun deleteBoard(
        @Path(value = "categorySeq") boardType: Int,
        @Body boardDeleteModel: BoardDeleteModel
    ): SpaceBody<Boolean>


    @GET("/v1/board-categories/{categorySeq}/boards/{boardSeq}")
    suspend fun getBoardDetail(
        @Path(value = "boardSeq") boardSeq: Int,
        @Path(value = "categorySeq") categorySeq: Int
    ): SpaceBody<BoardDetail>

    @POST("/v1/board-categories/{categorySeq}/boards/{boardSeq}/comments")
    suspend fun setComment(
        @Path(value = "boardSeq") boardSeq: Int,
        @Path(value = "categorySeq") categorySeq: Int,
        @Body boardCommentModel: BoardCommentModel
    ): SpaceBody<List<BoardComment>>

    @HTTP(
        method = "DELETE",
        path = "/v1/board-categories/{categorySeq}/boards/{boardSeq}/comments",
        hasBody = true
    )
    suspend fun deleteComment(
        @Path(value = "boardSeq") boardSeq: Int,
        @Path(value = "categorySeq") categorySeq: Int,
        @Body boardCommentDeleteModel: BoardCommentDeleteModel
    ): SpaceBody<Boolean>

}