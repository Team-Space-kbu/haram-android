package com.space.shared.model

import com.space.shared.data.notice.Notice
import com.space.shared.data.notice.NoticeType

data class NoticeDetailModel(
    val noticeType: NoticeType,
    val notice: Notice
)
