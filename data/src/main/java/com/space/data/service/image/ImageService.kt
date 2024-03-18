package com.space.data.service.image

import com.space.shared.data.image.StorageImage
import java.io.File

interface ImageService {
    suspend fun postImage(file: File, type: String): StorageImage
}