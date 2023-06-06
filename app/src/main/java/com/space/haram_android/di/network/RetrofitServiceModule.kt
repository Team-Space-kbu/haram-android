package com.space.haram_android.di.network

import com.space.haram_android.service.AuthService
import com.space.haram_android.service.HomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RetrofitServiceModule {

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
        @LoginNetworkModule.LoginRetrofit retrofit: Retrofit
    ): AuthService {
        return retrofit.create(AuthService::class.java)
    }

}