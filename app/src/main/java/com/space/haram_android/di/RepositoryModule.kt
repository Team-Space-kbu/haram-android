package com.space.haram_android.di

import com.space.haram_android.usecase.function.book.BookRepository
import com.space.haram_android.usecase.function.book.BookRepositoryImpl
import com.space.haram_android.usecase.function.chapel.ChapelRepository
import com.space.haram_android.usecase.function.chapel.ChapelRepositoryImpl
import com.space.haram_android.usecase.home.HomeRepository
import com.space.haram_android.usecase.home.HomeRepositoryImpl
import com.space.haram_android.usecase.intranet.IntranetRepository
import com.space.haram_android.usecase.intranet.IntranetRepositoryImpl
import com.space.haram_android.usecase.login.AuthRepository
import com.space.haram_android.usecase.login.AuthRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindLoginRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindIntranetRepository(impl: IntranetRepositoryImpl): IntranetRepository

    @Binds
    abstract fun bindChapelRepository(impl: ChapelRepositoryImpl): ChapelRepository

    @Binds
    abstract fun bindBookRepository(impl: BookRepositoryImpl): BookRepository

}