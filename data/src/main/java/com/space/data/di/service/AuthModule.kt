package com.space.data.di.service

import com.space.data.rest.AuthApi
import com.space.data.service.auth.AuthService
import com.space.data.service.auth.AuthServiceImpl
import com.space.shared.common.annotation.SpaceLoginModule
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AuthModule {
    @Binds
    @Singleton
    abstract fun bindAuthService(
        impl: AuthServiceImpl
    ): AuthService
}

@Module
@InstallIn(SingletonComponent::class)
internal class AuthApiModule {
    @Singleton
    @Provides
    fun provideLoginService(
        @SpaceLoginModule retrofit: Retrofit
    ): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }
}
