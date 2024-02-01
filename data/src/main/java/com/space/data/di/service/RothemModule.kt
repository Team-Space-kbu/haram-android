package com.space.data.di.service

import com.space.data.rest.RothemApi
import com.space.data.service.rothem.RothemService
import com.space.data.service.rothem.RothemServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class RothemModule {
    @Binds
    @Singleton
    abstract fun bindsRothemService(
        impl: RothemServiceImpl
    ): RothemService

}

@Module
@InstallIn(SingletonComponent::class)
internal class RothemApiModule {
    @Singleton
    @Provides
    fun provideRothemApiService(
        retrofit: Retrofit
    ): RothemApi {
        return retrofit.create(RothemApi::class.java)
    }
}