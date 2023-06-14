package com.space.haram_android.di

import com.space.haram_android.repository.function.book.BookRepository
import com.space.haram_android.repository.function.book.BookRepositoryImpl
import com.space.haram_android.repository.function.chapel.ChapelRepository
import com.space.haram_android.repository.function.chapel.ChapelRepositoryImpl
import com.space.haram_android.repository.home.HomeRepository
import com.space.haram_android.repository.home.HomeRepositoryImpl
import com.space.haram_android.repository.function.intranet.IntranetRepository
import com.space.haram_android.repository.function.intranet.IntranetRepositoryImpl
import com.space.haram_android.repository.login.AuthRepository
import com.space.haram_android.repository.login.AuthRepositoryImpl
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