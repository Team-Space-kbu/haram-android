package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.bible.BibleChapter
import com.space.shared.data.bible.BibleInfo
import retrofit2.http.GET
import retrofit2.http.Query

interface BibleApi {
    @GET("/v1/bibles/home")
    suspend fun getBible(): SpaceBody<BibleInfo>

    @GET("/v1/bibles/chapter")
    suspend fun getBooks(
        @Query("bibleType") type: String? = "RT",
        @Query("book") book: String,
        @Query("chapter") chapter: String,
    ): SpaceBody<List<BibleChapter>>
}