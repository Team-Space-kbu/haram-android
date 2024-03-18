package com.space.data.service.image

import com.space.data.rest.ImageApi
import com.space.shared.data.image.StorageImage
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import javax.inject.Inject

internal class ImageServiceImpl @Inject constructor(
    private val imageApi: ImageApi
) : ImageService {
    override suspend fun postImage(file: File, type: String): StorageImage {
        return runBlocking {
            imageApi.postImage(
                MultipartBody.Part.createFormData(
                    "multipartFile",
                    file.name,
                    file.asRequestBody()
                ),
                type.toRequestBody("text/plain".toMediaTypeOrNull())
            ).data
        }
    }
}