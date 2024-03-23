package com.space.data.service.notice_space

import com.space.data.rest.SpaceNotice
import com.space.shared.data.notice_space.BannerNotice
import com.space.shared.data.notice_space.BibleNotice
import com.space.shared.data.rothem.RothemNotice
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class NoticeSpaceServiceImpl @Inject constructor(
    private val spaceNotice: SpaceNotice
):NoticeSpaceService {
    override suspend fun getNoticeBanner(seq: String): BannerNotice {
        return runBlocking {
            spaceNotice.getNoticeBanner(seq).data
        }
    }

    override suspend fun getNoticeRothem(seq: String): RothemNotice {
        return runBlocking {
            spaceNotice.getNoticeRothem(seq).data
        }
    }

    override suspend fun getNoticeBible(seq: String): BibleNotice {
        return runBlocking {
            spaceNotice.getNoticeBible(seq).data
        }
    }
}