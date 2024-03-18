package com.space.shared.model


data class BoardModel(
    val title: String,
    val contents: String,
    val fileRequests: List<ImageModel>,
    val isAnonymous: Boolean = false
)
