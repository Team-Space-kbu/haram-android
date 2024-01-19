package com.space.repository.di

import com.space.repository.api.AuthApi
import com.space.repository.api.BibleApi
import com.space.repository.api.BoardApi
import com.space.repository.api.BookApi
import com.space.repository.api.ChapelApi
import com.space.repository.api.HomeApi
import com.space.repository.api.MileageApi
import com.space.repository.api.PartnersApi
import com.space.shared.common.annotation.SpaceLoginModule
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
    fun provideLoginService(
        @SpaceLoginModule retrofit: Retrofit
    ): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

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

    @Singleton
    @Provides
    fun provideChapelService(
        retrofit: Retrofit
    ): ChapelApi {
        return retrofit.create(ChapelApi::class.java)
    }

    @Singleton
    @Provides
    fun providePartnersService(
        retrofit: Retrofit
    ): PartnersApi {
        return retrofit.create(PartnersApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBibleApiService(
        retrofit: Retrofit
    ): BibleApi {
        return retrofit.create(BibleApi::class.java)
    }

    @Singleton
    @Provides
    fun provideBoardApiService(
        retrofit: Retrofit
    ): BoardApi {
        return retrofit.create(BoardApi::class.java)
    }
}

