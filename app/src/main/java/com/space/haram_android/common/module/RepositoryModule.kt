package com.space.haram_android.common.module

import com.space.haram_android.repository.login.LoginRepository
import com.space.haram_android.repository.login.LoginRepositoryImpl
import com.space.haram_android.repository.token.TokenRepository
import com.space.haram_android.repository.token.TokenRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLoginRepository(impl: LoginRepositoryImpl): LoginRepository

    @Binds
    abstract fun bindTokenRepository(impl: TokenRepositoryImpl): TokenRepository
}