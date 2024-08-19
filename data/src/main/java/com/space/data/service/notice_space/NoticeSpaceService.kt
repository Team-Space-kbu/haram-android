package com.space.data.service.notice_space

import com.space.shared.data.notice_space.NoticeSpace


interface NoticeSpaceService {

    suspend fun getNotice(
        seq: String,
    ): NoticeSpace

}