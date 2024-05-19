package com.space.domain.image

import com.space.data.service.image.ImageService
import com.space.domain.UseCase
import com.space.shared.type.ImageType
import com.space.space_annotation.annotation.IoDispatcher
import com.space.shared.data.image.StorageImage
import kotlinx.coroutines.CoroutineDispatcher
import java.io.File
import javax.inject.Inject

class ImageUseCase @Inject constructor(
    @IoDispatcher dispatcher: CoroutineDispatcher,
    private val imageService: ImageService
) : UseCase<Pair<File, ImageType>, StorageImage>(dispatcher) {
    override suspend fun execute(param: Pair<File, ImageType>): StorageImage {
        return imageService.postImage(param.first, param.second.name)
    }
}