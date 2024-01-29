package com.space.data.di.service

import com.space.data.rest.TimetableApi
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
internal abstract class TimetableModule {
    @Binds
    @Singleton
    abstract fun bindsTimetableService(
        impl: TimetableServiceImpl
    ): TimetableService

}

@Module
@InstallIn(SingletonComponent::class)
internal class TimetableApiModule {
    @Singleton
    @Provides
    fun provideTimetableApiService(
        retrofit: Retrofit
    ): TimetableApi {
        return retrofit.create(TimetableApi::class.java)
    }
}