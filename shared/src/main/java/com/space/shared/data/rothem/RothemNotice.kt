package com.space.shared.data.rothem

import com.space.shared.data.notice_space.NoticeSpace

data class RothemNoticeDetail(
    val noticeSeq: Int,
    val title: String,
    val content: String,
    val thumbnailPath: String,
    val createdBy: String,
    val createdAt: String,
    val modifiedBy: String,
    val modifiedAt: String
)

data class RothemNotice(
    val noticeResponse: RothemNoticeDetail,
    val noticeFileResponses: List<RothemNoticeFile>
) {
    fun toSpaceNotice() =
        NoticeSpace(
            noticeResponse.title,
            noticeResponse.content,
            noticeResponse.createdBy,
            noticeResponse.createdAt,
            noticeFileResponses.map { it.filePath }
        )
}

data class RothemNoticeFile(
    val seq: Int,
    val noticeSeq: Int,
    val sortNum: Int,
    val filePath: String,
    val createdBy: String,
    val createdAt: String,
    val modifiedBy: String,
    val modifiedAt: String
)