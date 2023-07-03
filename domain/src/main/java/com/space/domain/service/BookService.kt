package com.space.domain.service

import com.space.data.ResponseBody
import com.space.data.response.book.BookDetailReq
import com.space.data.response.book.BookHomeReq
import com.space.data.response.book.data.SearchResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("/v1/function/library")
    suspend fun getBookHome(): Response<ResponseBody<BookHomeReq>>

    @GET("/v1/function/library/search")
    suspend fun getBokSearch(
        @Query("text") text: String
    ): Response<ResponseBody<List<SearchResultModel>>>

    @GET("/v1/function/library/info")
    suspend fun getBookDetail(
        @Query("text") details: String
    ): Response<ResponseBody<BookDetailReq>>


}