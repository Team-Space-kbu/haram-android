package com.space.shared.model

import com.space.shared.data.notice_bible.NoticeType

data class NoticeSearchModel(
    val noticeType: NoticeType,
    val page: String? = "1"
)
