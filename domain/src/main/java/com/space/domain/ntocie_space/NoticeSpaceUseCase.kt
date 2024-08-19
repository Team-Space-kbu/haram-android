package com.space.domain.ntocie_space

import com.space.data.service.notice_space.NoticeSpaceService
import com.space.domain.UseCase
import com.space.shared.annotation.IoDispatcher
import com.space.shared.data.notice_space.NoticeSpace
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class NoticeSpaceUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val noticeSpaceService: NoticeSpaceService
) : UseCase<String, NoticeSpace>(dispatcher) {

    override suspend fun execute(param: String): NoticeSpace {
        return noticeSpaceService.getNotice(param)
    }
}