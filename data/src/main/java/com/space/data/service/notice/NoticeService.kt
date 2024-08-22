package com.space.data.service.notice

import com.space.shared.SpaceBody
import com.space.shared.data.notice_bible.NoticeDetail
import com.space.shared.data.notice_bible.NoticeHome
import com.space.shared.data.notice_bible.NoticeSearch
import com.space.shared.data.notice_bible.NoticeType
import com.space.shared.model.NoticeDetailModel


interface NoticeService {
    suspend fun getNoticeHome(): SpaceBody<NoticeHome>

    suspend fun getNoticeSearch(
        noticeType: NoticeType,
        page: String? = "1"
    ): SpaceBody<NoticeSearch>

    suspend fun getNoticeDetail(
        noticeDetailModel: NoticeDetailModel
    ): SpaceBody<NoticeDetail>

}