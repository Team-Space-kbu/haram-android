package com.space.repository.di

import com.space.repository.api.HomeApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal class ApiModule {

    @Singleton
    @Provides
    fun provideHomeService(
        retrofit: Retrofit
    ): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

}

