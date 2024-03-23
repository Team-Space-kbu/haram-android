package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.notice_space.BannerNotice
import com.space.shared.data.notice_space.BibleNotice
import com.space.shared.data.rothem.RothemNotice
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceNotice {

    @GET("/v1/banners/notices/{seq}")
    suspend fun getNoticeBanner(
        @Path("seq") seq: String,
    ): SpaceBody<BannerNotice>


    @GET("/v1/rothem/notices/{seq}")
    suspend fun getNoticeRothem(
        @Path("seq") seq: String,
    ): SpaceBody<RothemNotice>


    @GET("/v1/bibles/notices/{seq}")
    suspend fun getNoticeBible(
        @Path("seq") seq: String,
    ): SpaceBody<BibleNotice>
}