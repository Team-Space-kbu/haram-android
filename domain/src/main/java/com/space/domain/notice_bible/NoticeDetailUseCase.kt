package com.space.domain.notice_bible

import com.space.data.service.notice.NoticeService
import com.space.domain.UseCase
import com.space.shared.annotation.IoDispatcher
import com.space.shared.data.notice.NoticeDetail
import com.space.shared.model.NoticeDetailModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class NoticeDetailUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val noticeService: NoticeService
) : UseCase<NoticeDetailModel, NoticeDetail>(dispatcher) {
    override suspend fun execute(param: NoticeDetailModel): NoticeDetail {
        return noticeService.getNoticeDetail(param).data
    }
}