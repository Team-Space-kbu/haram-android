package com.space.data.service.notice

import com.space.shared.SpaceBody
import com.space.shared.data.notice.NoticeDetail
import com.space.shared.data.notice.NoticeHome
import com.space.shared.data.notice.NoticeSearch
import com.space.shared.data.notice.NoticeType
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