package com.space.domain.usecase.ntocie_space

import com.space.data.service.notice_space.NoticeSpaceService
import com.space.domain.UseCase
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.rothem.RothemNotice
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class NoticeRothemUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val noticeSpaceService: NoticeSpaceService
) : UseCase<String, RothemNotice>(dispatcher) {
    override suspend fun execute(param: String): RothemNotice {
        return noticeSpaceService.getNoticeRothem(param)
    }
}