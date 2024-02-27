package com.space.shared.model

import com.space.shared.data.notice.NoticeType

data class NoticeSearchModel(
    val noticeType: NoticeType,
    val page: String? = "1"
)
