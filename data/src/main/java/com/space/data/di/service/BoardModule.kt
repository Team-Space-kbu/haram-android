package com.space.data.di.service

import com.space.data.rest.BoardApi
import com.space.data.service.board.BoardService
import com.space.data.service.board.BoardServiceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class BoardModule {
    @Binds
    @Singleton
    abstract fun bindsBoardService(
        impl: BoardServiceImpl
    ): BoardService
}

@Module
@InstallIn(SingletonComponent::class)
internal class BoardApiModule {
    @Singleton
    @Provides
    fun provideBoardApiService(
        retrofit: Retrofit
    ): BoardApi {
        return retrofit.create(BoardApi::class.java)
    }
}