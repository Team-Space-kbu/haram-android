package com.space.data.res.intranet

data class IntranetTokenRes(
    val token: String? = null,
    val xsrf_token: String? = null,
    val laravel_session: String? = null
)
