package com.space.data.service.notice

import com.space.data.rest.NoticeApi
import com.space.shared.SpaceBody
import com.space.shared.data.notice.NoticeHome
import javax.inject.Inject

internal class NoticeServiceImpl @Inject constructor(
    private val noticeApi: NoticeApi
) : NoticeService {
    override suspend fun getNoticeHome(): SpaceBody<NoticeHome> {
        return noticeApi.getNoticeHome()
    }
}