package com.space.shared.data.notice

data class NoticeSearch(
    val start: String,
    val end: String,
    val notices:List<Notice>
)
