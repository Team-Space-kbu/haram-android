package com.space.shared.model

import com.space.shared.data.notice_bible.Notice
import com.space.shared.data.notice_bible.NoticeType

data class NoticeDetailModel(
    val noticeType: NoticeType,
    val notice: Notice
)
