package com.space.data.di.service

import com.space.data.rest.ClassRoomApi
import com.space.data.rest.PartnersApi
import com.space.data.service.class_room.ClassRoomService
import com.space.data.service.class_room.ClassRoomServiceImpl
import com.space.data.service.partners.PartnersService
import com.space.data.service.partners.PartnersServiceImpl
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
        impl: ClassRoomServiceImpl
    ): ClassRoomService
}

@Module
@InstallIn(SingletonComponent::class)
internal class CourseApiModule{

    @Singleton
    @Provides
    fun provideCourseService(
        retrofit: Retrofit
    ): ClassRoomApi {
        return retrofit.create(ClassRoomApi::class.java)
    }
}
