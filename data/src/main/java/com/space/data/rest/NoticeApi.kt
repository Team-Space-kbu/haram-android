package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.notice_bible.NoticeDetail
import com.space.shared.data.notice_bible.NoticeHome
import com.space.shared.data.notice_bible.NoticeSearch
import retrofit2.http.GET
import retrofit2.http.Query

interface NoticeApi {

    @GET("/v1/notice")
    suspend fun getNoticeHome(): SpaceBody<NoticeHome>

    @GET("/v1/notice/search")
    suspend fun getNoticeSearch(
        @Query("type") noticeType: String,
        @Query("page") page: String? = "1",
    ): SpaceBody<NoticeSearch>

    @GET("/v1/notice/detail")
    suspend fun getNoticeDetail(
        @Query("type") noticeType: String,
        @Query("path") page: String,
    ): SpaceBody<NoticeDetail>

}