package com.space.data.di.service

import com.space.data.rest.AuthApi
import com.space.data.service.signup.SignupService
import com.space.data.service.signup.SignupServiceImpl
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
internal abstract class SignupModule {
    @Binds
    @Singleton
    abstract fun bindSignupService(
        impl: SignupServiceImpl
    ): SignupService
}
