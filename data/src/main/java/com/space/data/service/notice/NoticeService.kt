package com.space.data.service.notice

import com.space.shared.SpaceBody
import com.space.shared.data.notice.NoticeHome

interface NoticeService {
    suspend fun getNoticeHome(): SpaceBody<NoticeHome>
}