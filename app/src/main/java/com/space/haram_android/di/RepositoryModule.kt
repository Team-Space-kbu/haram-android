package com.space.haram_android.di

import com.space.domain.usecase.book.BookRepository
import com.space.domain.usecase.book.BookRepositoryImpl
import com.space.domain.usecase.chapel.ChapelRepository
import com.space.domain.usecase.chapel.ChapelRepositoryImpl
import com.space.domain.usecase.home.HomeRepository
import com.space.domain.usecase.home.HomeRepositoryImpl
import com.space.domain.usecase.intranet.IntranetRepository
import com.space.domain.usecase.intranet.IntranetRepositoryImpl
import com.space.domain.usecase.login.AuthRepository
import com.space.domain.usecase.login.AuthRepositoryImpl

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