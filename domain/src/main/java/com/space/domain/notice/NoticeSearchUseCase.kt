package com.space.domain.notice

import com.space.data.service.notice.NoticeService
import com.space.domain.UseCase
import com.space.builder_annotation.annotation.IoDispatcher
import com.space.shared.data.notice.NoticeSearch
import com.space.shared.model.NoticeSearchModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class NoticeSearchUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val noticeService: NoticeService
) : UseCase<NoticeSearchModel, NoticeSearch>(dispatcher) {
    override suspend fun execute(param: NoticeSearchModel): NoticeSearch {
        return noticeService.getNoticeSearch(param.noticeType, param.page).data
    }


}