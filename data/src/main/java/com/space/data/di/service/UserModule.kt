package com.space.data.di.service

import com.space.data.rest.UserApi
import com.space.data.service.user.UserService
import com.space.data.service.user.UserServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class UserModule {
    @Binds
    @Singleton
    abstract fun bindUserService(
        impl: UserServiceImpl
    ): UserService
}

@Module
@InstallIn(SingletonComponent::class)
internal class UserApiModule {
    @Singleton
    @Provides
    fun provideUserService(
        retrofit: Retrofit
    ): UserApi {
        return retrofit.create(UserApi::class.java)
    }
}
