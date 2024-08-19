package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.notice_space.NoticeSpace
import retrofit2.http.GET
import retrofit2.http.Path

interface SpaceNotice {

    @GET("/v2/space/notice/{seq}")
    suspend fun getNoticeRothem(
        @Path("seq") seq: String,
    ): SpaceBody<NoticeSpace>

}