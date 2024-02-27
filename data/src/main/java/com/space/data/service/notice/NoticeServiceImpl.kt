package com.space.data.service.notice

import com.space.data.rest.NoticeApi
import com.space.shared.SpaceBody
import com.space.shared.data.notice.NoticeDetail
import com.space.shared.data.notice.NoticeHome
import com.space.shared.data.notice.NoticeSearch
import com.space.shared.data.notice.NoticeType
import com.space.shared.model.NoticeDetailModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class NoticeServiceImpl @Inject constructor(
    private val noticeApi: NoticeApi
) : NoticeService {
    override suspend fun getNoticeHome(): SpaceBody<NoticeHome> {
        return runBlocking {
            noticeApi.getNoticeHome()
        }
    }

    override suspend fun getNoticeSearch(
        noticeType: NoticeType,
        page: String?
    ): SpaceBody<NoticeSearch> {
        return runBlocking {
            noticeApi.getNoticeSearch(noticeType.key, page)
        }
    }

    override suspend fun getNoticeDetail(
        noticeDetailModel: NoticeDetailModel
    ): SpaceBody<NoticeDetail> {
        return runBlocking {
            noticeApi.getNoticeDetail(
                noticeDetailModel.noticeType.key,
                noticeDetailModel.notice.path
            )
        }
    }
}