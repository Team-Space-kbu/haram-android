package com.space.data.di.service

import com.space.data.rest.BookApi
import com.space.data.service.book.BookService
import com.space.data.service.book.BookServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class BookModule {
    @Binds
    @Singleton
    abstract fun bindsBookService(
        impl: BookServiceImpl
    ): BookService
}

@Module
@InstallIn(SingletonComponent::class)
internal class BookApiModule {
    @Singleton
    @Provides
    fun provideBookService(
        retrofit: Retrofit
    ): BookApi {
        return retrofit.create(BookApi::class.java)
    }
}