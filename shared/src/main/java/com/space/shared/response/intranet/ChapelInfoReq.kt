package com.space.shared.response.intranet

data class ChapelInfoReq(
    val regulateDays: String? = null,
    val attendanceDays: String? = null,
    val lateDays: String? = null,
    val confirmationDays: String? = null,
    val entireDays: String? = null
)
