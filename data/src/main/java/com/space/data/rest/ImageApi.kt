package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.image.StorageImage
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ImageApi {

    @Multipart
    @POST("/v1/img/storage")
    suspend fun postImage(
        @Part filePart: MultipartBody.Part,
        @Part("aggregateType") aggregateType: RequestBody
    ): SpaceBody<StorageImage>
}