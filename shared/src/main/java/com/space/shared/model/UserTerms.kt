package com.space.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class UserTerms(
    val termsSeq: Int,
    val termsAgreeYn: String
)
