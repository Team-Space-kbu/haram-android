package com.space.data.di.service

import com.space.data.service.login.LoginService
import com.space.data.service.login.LoginServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class LoginModule {
    @Binds
    @Singleton
    abstract fun bindsLoginService(
        impl: LoginServiceImpl
    ): LoginService
}