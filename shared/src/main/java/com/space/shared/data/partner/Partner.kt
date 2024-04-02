package com.space.shared.data.partner

import kotlinx.serialization.Serializable

@Serializable
data class Partner(
    val id: String,
    val businessName: String,
    val address: String,
    val tag: String,
    val image: String,
)
