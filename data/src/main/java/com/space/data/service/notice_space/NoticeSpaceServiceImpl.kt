package com.space.data.service.notice_space

import com.space.data.rest.SpaceNotice
import com.space.shared.data.notice_space.NoticeSpace
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class NoticeSpaceServiceImpl @Inject constructor(
    private val spaceNotice: SpaceNotice
) : NoticeSpaceService {

    override suspend fun getNotice(seq: String): NoticeSpace {
        return runBlocking {
            spaceNotice.getNoticeRothem(seq).data
        }
    }

}