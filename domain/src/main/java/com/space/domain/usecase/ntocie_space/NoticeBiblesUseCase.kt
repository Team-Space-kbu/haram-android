package com.space.domain.usecase.ntocie_space

import com.space.data.service.notice_space.NoticeSpaceService
import com.space.domain.UseCase
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.notice_space.BibleNotice
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class NoticeBiblesUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val noticeSpaceService: NoticeSpaceService
) : UseCase<String, BibleNotice>(dispatcher) {
    override suspend fun execute(param: String): BibleNotice {
        return noticeSpaceService.getNoticeBible(param)
    }
}