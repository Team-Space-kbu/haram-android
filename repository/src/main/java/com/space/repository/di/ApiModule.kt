package com.space.repository.di

import com.space.repository.api.BookApi
import com.space.repository.api.HomeApi
import com.space.repository.api.MileageApi
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

    @Singleton
    @Provides
    fun provideBookService(
        retrofit: Retrofit
    ): BookApi {
        return retrofit.create(BookApi::class.java)
    }

    @Singleton
    @Provides
    fun provideMileageService(
        retrofit: Retrofit
    ): MileageApi {
        return retrofit.create(MileageApi::class.java)
    }

}

