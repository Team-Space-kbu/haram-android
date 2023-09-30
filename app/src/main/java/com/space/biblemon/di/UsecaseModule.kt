package com.space.biblemon.di

import com.space.domain.usecase.book.BookUsecase
import com.space.domain.usecase.book.BookUsecaseImpl
import com.space.domain.usecase.chapel.ChapelRepository
import com.space.domain.usecase.chapel.ChapelRepositoryImpl
import com.space.domain.usecase.home.HomeUseCase
import com.space.domain.usecase.home.HomeUseCaseImpl
import com.space.domain.usecase.intranet.IntranetRepository
import com.space.domain.usecase.intranet.IntranetRepositoryImpl
import com.space.domain.usecase.login.AuthUseCase
import com.space.domain.usecase.login.AuthUseCaseImpl

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UsecaseModule {

    @Binds
    abstract fun bindAuthUsecase(impl: AuthUseCaseImpl): AuthUseCase

    @Binds
    abstract fun bindHomeUseCase(impl: HomeUseCaseImpl): HomeUseCase

    @Binds
    abstract fun bindIntranetRepository(impl: IntranetRepositoryImpl): IntranetRepository

    @Binds
    abstract fun bindChapelRepository(impl: ChapelRepositoryImpl): ChapelRepository

    @Binds
    abstract fun bindBookRepository(impl: BookUsecaseImpl): BookUsecase

}