package com.space.data.di.service

import com.space.data.rest.BibleApi
import com.space.data.service.bible.BibleService
import com.space.data.service.bible.BibleServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
internal abstract class BibleModule {
    @Binds
    @Singleton
    abstract fun bindsBibleService(
        impl: BibleServiceImpl
    ): BibleService
}

@Module
@InstallIn(SingletonComponent::class)
internal class BibleApiModule {
    @Singleton
    @Provides
    fun provideBibleApiService(
        retrofit: Retrofit
    ): BibleApi {
        return retrofit.create(BibleApi::class.java)
    }
}