package com.space.shared.data.partner

import kotlinx.serialization.Serializable

@Serializable
data class Partner(
    val id: String? = null,
    val businessName: String? = null,
    val address: String? = null,
    val tag: String? = null,
    val image: String? = null,
)
