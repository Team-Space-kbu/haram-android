package com.space.shared.data.notice_space

data class BibleNotice(
    val bibleNoticeSeq: Int,
    val title: String,
    val content: String,
    val thumbnailPath: String,
    val createdBy: String,
    val createdAt: String,
    val modifiedBy: String,
    val modifiedAt: String,
    val bibleNoticeFileResponses: List<BibleNoticeFile>
) {
    fun toNoticeSpace() =
        NoticeSpace(
            title,
            content,
            createdBy,
            createdAt,
            bibleNoticeFileResponses.map { it.filePath }.toList()
        )
}


data class BibleNoticeFile(
    val seq: Int,
    val bibleNoticeSeq: Int,
    val sortNum: Int,
    val filePath: String,
    val createdBy: String,
    val createdAt: String,
    val modifiedBy: String,
    val modifiedAt: String,
)