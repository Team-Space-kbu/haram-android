package com.space.shared.data.bible

data class NoticeBible(
    val bibleNoticeSeq: Int,
    val title: String,
    val content: String,
    val thumbnailPath: String,
    val createdBy: String,
    val createdAt: String,
    val modifiedBy: String,
    val modifiedAt: String,

)
