package com.space.domain.service

import com.space.data.ResponseBody
import com.space.data.response.book.BookHomeReq
import com.space.data.response.book.data.BookDetailInfo
import com.space.data.response.book.data.BookKeepInfo
import com.space.data.response.book.data.SearchResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface BookService {

    @GET("/v1/function/library")
    suspend fun getBookHome(): Response<ResponseBody<BookHomeReq>>

    @GET("/v1/function/library/search/{text}")
    suspend fun getBokSearch(
        @Path(value = "text") text: String
    ): Response<ResponseBody<List<SearchResultModel>>>

    @GET("/v1/function/library/detail/info/{detail}")
    suspend fun getBookDetailInfo(
        @Path(value = "detail") detail: Int
    ): Response<ResponseBody<BookDetailInfo>>

    @GET("/v1/function/library/detail/keep/{path}")
    suspend fun getBookDetailKeep(
        @Path(value = "path") detail: Int
    ): Response<ResponseBody<List<BookKeepInfo>>>


}