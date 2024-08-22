package com.space.shared.data.notice_bible

data class NoticeSearch(
    val start: String,
    val end: String,
    val notices:List<Notice>
)
