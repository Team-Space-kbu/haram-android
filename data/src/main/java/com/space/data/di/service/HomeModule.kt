package com.space.data.di.service

import com.space.data.rest.HomeApi
import com.space.data.service.home.HomeService
import com.space.data.service.home.HomeServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class HomeModule {
    @Binds
    @Singleton
    abstract fun bindsHomeService(
        impl: HomeServiceImpl
    ): HomeService
}

@Module
@InstallIn(SingletonComponent::class)
internal class HomeApiModule {
    @Singleton
    @Provides
    fun provideHomeService(
        retrofit: Retrofit
    ): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }
}