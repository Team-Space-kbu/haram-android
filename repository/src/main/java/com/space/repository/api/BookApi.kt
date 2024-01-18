package com.space.repository.api

import com.space.shared.SpaceBody
import com.space.shared.data.book.BookHome
import com.space.shared.data.book.BookSearch
import com.space.shared.data.book.BookDetailInfo
import com.space.shared.data.book.BookEtc
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApi {

    @GET("/v1/library")
    suspend fun getBookHome(): SpaceBody<BookHome>

    @GET("/v1/library/search")
    suspend fun getBokSearch(
        @Query(value = "q") search: String,
        @Query(value = "p") page: Int? = null
    ): SpaceBody<BookSearch>

    @GET("/v1/library/detail/info/{detail}")
    suspend fun getBookDetailInfo(
        @Path(value = "detail") detail: Int
    ): SpaceBody<BookDetailInfo>

    @GET("/v1/library/detail/keep/{path}")
    suspend fun getBookDetailKeep(
        @Path(value = "path") detail: Int
    ): SpaceBody<BookEtc>


}