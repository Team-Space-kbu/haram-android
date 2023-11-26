package com.space.domain.service

import SpaceBody
import response.book.BookHomeReq
import response.book.BookSearchReq
import response.book.BookDetailInfo
import response.book.BookDetailKeep
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookService {

    @GET("/v1/library")
    suspend fun getBookHome(): Response<SpaceBody<BookHomeReq>>

    @GET("/v1/library/search")
    suspend fun getBokSearch(
        @Query(value = "q") search: String,
        @Query(value = "p") page: Int? = null
    ): Response<SpaceBody<BookSearchReq>>

    @GET("/v1/library/detail/info/{detail}")
    suspend fun getBookDetailInfo(
        @Path(value = "detail") detail: Int
    ): Response<SpaceBody<BookDetailInfo>>

    @GET("/v1/library/detail/keep/{path}")
    suspend fun getBookDetailKeep(
        @Path(value = "path") detail: Int
    ): Response<SpaceBody<BookDetailKeep>>


}