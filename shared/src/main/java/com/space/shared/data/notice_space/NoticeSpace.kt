package com.space.shared.data.notice_space

data class NoticeSpace(
    val title: String,
    val content: String,
    val createdBy: String,
    val createdAt: String,
    val imageFiles: List<String>
)