package com.space.haram_android.common.module

import com.space.haram_android.service.RefreshService
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
    fun provideRefreshService(
        retrofit: Retrofit
    ): RefreshService {
        return retrofit.create(RefreshService::class.java)
    }

}