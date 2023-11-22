package com.space.domain2.api


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Singleton
    @Provides
    fun provideHomeService(
        retrofit: Retrofit
    ): SpaceHomeApi {
        return retrofit.create(SpaceHomeApi::class.java)
    }

}