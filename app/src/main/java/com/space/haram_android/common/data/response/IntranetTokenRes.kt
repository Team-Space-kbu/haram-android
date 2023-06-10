package com.space.haram_android.common.data.response

data class IntranetTokenRes(
    val token: String? = null,
    val xsrf_token: String? = null,
    val laravel_session: String? = null
)
