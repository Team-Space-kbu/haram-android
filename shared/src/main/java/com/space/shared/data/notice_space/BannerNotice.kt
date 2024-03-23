package com.space.shared.data.notice_space

data class BannerNotice(
    val bannerSeq: Int,
    val title: String,
    val content: String,
    val thumbnailPath: String,
    val department: String,
    val bannerFileResponses: List<BannerFile>
) {
    fun toNoticeSpace() =
        NoticeSpace(
            title,
            content,
            department,
            "",
            bannerFileResponses.map { it.filePath }.toList()
        )
}

data class BannerFile(
    val seq: Int,
    val bannerSeq: Int,
    val filePath: String,
    val createdBy: String,
    val createdAt: String,
    val modifiedBy: String,
    val modifiedAt: String,
)
