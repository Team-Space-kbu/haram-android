package com.space.shared.model

data class ImageModel(
    val tempFilePath: String,
    val fileName: String,
    val fileExt: String,
    val fileSize: Float,
    val sortNum: Int
)
