package com.space.data.di.service

import com.space.data.rest.MileageApi
import com.space.data.service.mileage.MileageService
import com.space.data.service.mileage.MileageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class MileageModule {
    @Binds
    @Singleton
    abstract fun bindsMileageService(
        impl: MileageServiceImpl
    ): MileageService
}

@Module
@InstallIn(SingletonComponent::class)
internal class MileageApiModule {
    @Singleton
    @Provides
    fun provideMileageService(
        retrofit: Retrofit
    ): MileageApi {
        return retrofit.create(MileageApi::class.java)
    }
}