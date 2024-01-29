package com.space.data.di.service

import com.space.data.rest.ChapelApi
import com.space.data.service.chpael.ChapelService
import com.space.data.service.chpael.ChapelServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class ChapelModule {
    @Binds
    @Singleton
    abstract fun bindsChapelService(
        impl: ChapelServiceImpl
    ): ChapelService
}

@Module
@InstallIn(SingletonComponent::class)
internal class ChapelApiModule {
    @Singleton
    @Provides
    fun provideChapelService(
        retrofit: Retrofit
    ): ChapelApi {
        return retrofit.create(ChapelApi::class.java)
    }
}