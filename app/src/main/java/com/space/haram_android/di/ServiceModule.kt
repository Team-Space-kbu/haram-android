package com.space.haram_android.di

import com.space.haram_android.common.annotation.IntranetModule
import com.space.haram_android.common.annotation.SpaceLoginModule
import com.space.haram_android.service.AuthService
import com.space.haram_android.service.HomeService
import com.space.haram_android.service.IntranetService
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
    ): HomeService {
        return retrofit.create(HomeService::class.java)
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
    fun provideIntranetService(
        @IntranetModule retrofit: Retrofit
    ): IntranetService {
        return retrofit.create(IntranetService::class.java)
    }

}