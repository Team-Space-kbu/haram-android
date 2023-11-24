package com.space.domain.di

import com.space.domain.service.AuthService
import com.space.domain.service.BibleService
import com.space.domain.service.BookService
import com.space.domain.api.HomeApi
import com.space.domain.service.IntranetLoginService
import com.space.domain.service.IntranetService
import com.space.domain.service.PartnersService
import com.space.shared.annotation.IntranetModule
import com.space.shared.annotation.SpaceLoginModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ServiceModule {

    @Singleton
    @Provides
    fun provideHomeService(
        retrofit: Retrofit
    ): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Singleton
    @Provides
    fun provideLoginService(
        @SpaceLoginModule retrofit: Retrofit
    ): AuthService {
        return retrofit.create(AuthService::class.java)
    }

    @Singleton
    @Provides
    fun provideIntranetLoginService(
        @IntranetModule retrofit: Retrofit
    ): IntranetLoginService {
        return retrofit.create(IntranetLoginService::class.java)
    }

    @Singleton
    @Provides
    fun provideIntranetService(
        retrofit: Retrofit
    ): IntranetService {
        return retrofit.create(IntranetService::class.java)
    }

    @Singleton
    @Provides
    fun provideBookService(
        retrofit: Retrofit
    ): BookService {
        return retrofit.create(BookService::class.java)
    }


    @Singleton
    @Provides
    fun providePartnersService(
        retrofit: Retrofit
    ): PartnersService {
        return retrofit.create(PartnersService::class.java)
    }

    @Singleton
    @Provides
    fun provideBibleService(
        retrofit: Retrofit
    ): BibleService {
        return retrofit.create(BibleService::class.java)
    }

}