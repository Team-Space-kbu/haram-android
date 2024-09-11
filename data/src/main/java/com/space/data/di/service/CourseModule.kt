package com.space.data.di.service

import com.space.data.rest.CourseApi
import com.space.data.service.class_room.CourseService
import com.space.data.service.class_room.CourseServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class CourseModule {

    @Binds
    @Singleton
    abstract fun bindsPartnersService(
        impl: CourseServiceImpl
    ): CourseService
}

@Module
@InstallIn(SingletonComponent::class)
internal class CourseApiModule{

    @Singleton
    @Provides
    fun provideCourseService(
        retrofit: Retrofit
    ): CourseApi {
        return retrofit.create(CourseApi::class.java)
    }
}
