package com.space.haram_android.service

import com.space.haram_android.common.data.response.book.BookDetailReq
import com.space.haram_android.common.data.response.book.BookHomeReq
import com.space.haram_android.common.data.response.book.data.SearchResultModel
import com.space.haram_android.repository.ResponseBody
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