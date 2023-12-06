package com.space.domain.di

import com.space.domain.service.AuthService
import com.space.domain.service.BibleService
import com.space.domain.service.PartnersService
import com.space.shared.common.annotation.SpaceLoginModule
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
    fun provideLoginService(
        @SpaceLoginModule retrofit: Retrofit
    ): AuthService {
        return retrofit.create(AuthService::class.java)
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