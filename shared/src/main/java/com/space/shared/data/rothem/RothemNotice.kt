package com.space.shared.data.rothem

data class RothemNoticeDetail(
    val noticeSeq: Int,
    val title:String,
    val content:String,
    val thumbnailPath:String,
    val createdBy:String,
    val createdAt:String,
    val modifiedBy:String,
    val modifiedAt:String
)

data class RothemNotice(
    val noticeResponse: RothemNoticeDetail,
    val noticeFileResponses: List<RothemNoticeFile>
)

data class RothemNoticeFile(
    val seq: Int,
    val noticeSeq: Int,
    val sortNum: Int,
    val filePath: String,
    val createdBy:String,
    val createdAt:String,
    val modifiedBy:String,
    val modifiedAt:String
)