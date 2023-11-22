package com.space.domain.service

import com.space.data.ResponseBody
import com.space.data.response.book.BookHomeReq
import com.space.data.response.book.BookSearchReq
import com.space.data.response.book.BookDetailInfo
import com.space.data.response.book.BookDetailKeep
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookService {

    @GET("/v1/library")
    suspend fun getBookHome(): Response<ResponseBody<BookHomeReq>>

    @GET("/v1/library/search")
    suspend fun getBokSearch(
        @Query(value = "q") search: String,
        @Query(value = "p") page: Int? = null
    ): Response<ResponseBody<BookSearchReq>>

    @GET("/v1/library/detail/info/{detail}")
    suspend fun getBookDetailInfo(
        @Path(value = "detail") detail: Int
    ): Response<ResponseBody<BookDetailInfo>>

    @GET("/v1/library/detail/keep/{path}")
    suspend fun getBookDetailKeep(
        @Path(value = "path") detail: Int
    ): Response<ResponseBody<BookDetailKeep>>


}