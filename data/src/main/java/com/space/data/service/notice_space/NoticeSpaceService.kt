package com.space.data.service.notice_space

import com.space.shared.data.notice_space.BannerNotice
import com.space.shared.data.notice_space.BibleNotice
import com.space.shared.data.rothem.RothemNotice


interface NoticeSpaceService {
    suspend fun getNoticeBanner(
        seq: String,
    ): BannerNotice

    suspend fun getNoticeRothem(
        seq: String,
    ): RothemNotice

    suspend fun getNoticeBible(
        seq: String,
    ): BibleNotice
}