package com.space.repository.di

import com.space.repository.BookService
import com.space.repository.HomeService
import com.space.repository.service.BookServiceImpl
import com.space.repository.service.HomeServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule{

    @Binds
    abstract fun bindsHomeService(
        homeService: HomeServiceImpl
    ): HomeService

    @Binds
    abstract fun bindsBookService(
        bookServiceImpl: BookServiceImpl
    ): BookService

}