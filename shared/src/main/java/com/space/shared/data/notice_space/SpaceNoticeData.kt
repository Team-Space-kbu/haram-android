package com.space.shared.data.notice_space

import com.space.shared.type.NoticeSpaceType
import kotlinx.serialization.Serializable

@Serializable
data class SpaceNoticeData(
    val seq: String,
    val noticeSpaceType: NoticeSpaceType
) {
    companion object{
        fun toSpaceType(data: String) =
            when (data) {
                "bibles" -> NoticeSpaceType.BIBLE
                "rothem" -> NoticeSpaceType.ROTHEM
                "banners" -> NoticeSpaceType.BANNER
                "space" -> NoticeSpaceType.SPACE
                else -> NoticeSpaceType.BANNER
            }
    }

}
