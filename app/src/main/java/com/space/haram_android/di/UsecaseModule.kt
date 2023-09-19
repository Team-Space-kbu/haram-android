package com.space.haram_android.di

import com.space.domain.usecase.book.BookUsecase
import com.space.domain.usecase.book.BookUsecaseImpl
import com.space.domain.usecase.chapel.ChapelRepository
import com.space.domain.usecase.chapel.ChapelRepositoryImpl
import com.space.domain.usecase.home.HomeRepository
import com.space.domain.usecase.home.HomeRepositoryImpl
import com.space.domain.usecase.intranet.IntranetRepository
import com.space.domain.usecase.intranet.IntranetRepositoryImpl
import com.space.domain.usecase.login.AuthUsecase
import com.space.domain.usecase.login.AuthUsecaseImpl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UsecaseModule {

    @Binds
    abstract fun bindAuthUsecase(impl: AuthUsecaseImpl): AuthUsecase

    @Binds
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindIntranetRepository(impl: IntranetRepositoryImpl): IntranetRepository

    @Binds
    abstract fun bindChapelRepository(impl: ChapelRepositoryImpl): ChapelRepository

    @Binds
    abstract fun bindBookRepository(impl: BookUsecaseImpl): BookUsecase

}