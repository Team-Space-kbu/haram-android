package com.space.domain.ntocie_space

import com.space.data.service.notice_space.NoticeSpaceService
import com.space.domain.UseCase
import com.space.space_annotation.annotation.IoDispatcher
import com.space.shared.data.notice_space.BannerNotice
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class NoticeBannerUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val noticeSpaceService: NoticeSpaceService
) : UseCase<String, BannerNotice>(dispatcher) {
    override suspend fun execute(param: String): BannerNotice {
        return noticeSpaceService.getNoticeBanner(param)
    }
}