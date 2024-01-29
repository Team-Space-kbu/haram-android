package com.space.data.di.service

import com.space.data.rest.PartnersApi
import com.space.data.service.partners.PartnersService
import com.space.data.service.partners.PartnersServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class PartnersModule {
    @Binds
    @Singleton
    abstract fun bindsPartnersService(
        impl: PartnersServiceImpl
    ): PartnersService
}

@Module
@InstallIn(SingletonComponent::class)
internal class PartnersApiModule{
    @Singleton
    @Provides
    fun providePartnersService(
        retrofit: Retrofit
    ): PartnersApi {
        return retrofit.create(PartnersApi::class.java)
    }
}
