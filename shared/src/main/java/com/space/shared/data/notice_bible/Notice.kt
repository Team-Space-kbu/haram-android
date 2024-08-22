package com.space.shared.data.notice_bible

import com.space.shared.data.Category
import com.space.shared.util.Formatter.formatToDate
import kotlinx.serialization.Serializable

@Serializable
data class Notice(
    val title: String,
    val name: String,
    val reg_date: String,
    val path: String,
    val loopnum: List<String>
){
    companion object {
        fun toCategory(notice: Notice): Category {
            return Category(
                notice.title,
                "${formatToDate(notice.reg_date)} | ${notice.name}",
                notice.path
            )
        }
    }
}
