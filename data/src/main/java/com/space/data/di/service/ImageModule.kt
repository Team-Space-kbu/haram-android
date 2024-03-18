package com.space.data.di.service

import com.space.data.rest.ImageApi
import com.space.data.rest.TimetableApi
import com.space.data.service.image.ImageService
import com.space.data.service.image.ImageServiceImpl
import com.space.data.service.timetable.TimetableService
import com.space.data.service.timetable.TimetableServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ImageModule {
    @Binds
    @Singleton
    abstract fun bindsImageService(
        impl: ImageServiceImpl
    ): ImageService

}

@Module
@InstallIn(SingletonComponent::class)
internal class ImageApiModule {
    @Singleton
    @Provides
    fun provideImageApiService(
        retrofit: Retrofit
    ): ImageApi {
        return retrofit.create(ImageApi::class.java)
    }
}