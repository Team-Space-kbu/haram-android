package com.space.data.di.service

import com.space.data.rest.IntranetApi
import com.space.data.service.intranet.IntranetService
import com.space.data.service.intranet.IntranetServiceImpl
import com.space.data.service.login.LoginService
import com.space.data.service.login.LoginServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
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

@Module
@InstallIn(SingletonComponent::class)
internal abstract class IntranetModule {
    @Binds
    @Singleton
    abstract fun bindsLoginService(
        impl: IntranetServiceImpl
    ): IntranetService
}


@Module
@InstallIn(SingletonComponent::class)
internal class IntranetApiModule {
    @Singleton
    @Provides
    fun provideIntranetApi(
        retrofit: Retrofit
    ): IntranetApi {
        return retrofit.create(IntranetApi::class.java)
    }
}