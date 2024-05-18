package com.space.domain.notice

import com.space.data.service.notice.NoticeService
import com.space.domain.NonParamUseCase
import com.space.builder_annotation.annotation.IoDispatcher
import com.space.shared.data.notice.NoticeHome
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class NoticeHomeUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val noticeService: NoticeService
) : NonParamUseCase<NoticeHome>(dispatcher) {
    override suspend fun execute(): NoticeHome {
        return noticeService.getNoticeHome().data
    }
}