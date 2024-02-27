package com.space.data.di.service

import com.space.data.rest.NoticeApi
import com.space.data.service.notice.NoticeService
import com.space.data.service.notice.NoticeServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NoticeModule {
    @Binds
    @Singleton
    abstract fun bindsNoticeService(
        impl: NoticeServiceImpl
    ): NoticeService

}

@Module
@InstallIn(SingletonComponent::class)
internal class NoticeApiModule {
    @Singleton
    @Provides
    fun provideNoticeApiService(
        retrofit: Retrofit
    ): NoticeApi {
        return retrofit.create(NoticeApi::class.java)
    }
}