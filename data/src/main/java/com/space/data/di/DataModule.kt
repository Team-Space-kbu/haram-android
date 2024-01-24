package com.space.data.di

import com.space.data.service.auth.AuthService
import com.space.data.service.partners.PartnersService
import com.space.data.service.auth.AuthServiceImpl
import com.space.data.service.bible.BibleService
import com.space.data.service.bible.BibleServiceImpl
import com.space.data.service.board.BoardService
import com.space.data.service.book.BookServiceImpl
import com.space.data.service.chpael.ChapelServiceImpl
import com.space.data.service.home.HomeServiceImpl
import com.space.data.service.mileage.MileageServiceImpl
import com.space.data.service.partners.PartnersServiceImpl
import com.space.data.service.board.BoardServiceImpl
import com.space.data.service.book.BookService
import com.space.data.service.chpael.ChapelService
import com.space.data.service.home.HomeService
import com.space.data.service.mileage.MileageService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    @Singleton
    abstract fun bindsAuthService(
        authServiceImpl: AuthServiceImpl
    ): AuthService

    @Binds
    @Singleton
    abstract fun bindsHomeService(
        homeService: HomeServiceImpl
    ): HomeService

    @Binds
    @Singleton
    abstract fun bindsBookService(
        bookServiceImpl: BookServiceImpl
    ): BookService

    @Binds
    @Singleton
    abstract fun bindsMileageService(
        mileageService: MileageServiceImpl
    ): MileageService

    @Binds
    @Singleton
    abstract fun bindsChapelService(
        chapelService: ChapelServiceImpl
    ): ChapelService

    @Binds
    @Singleton
    abstract fun bindsPartnersService(
        partnersServiceImpl: PartnersServiceImpl
    ): PartnersService

    @Binds
    @Singleton
    abstract fun bindsBibleService(
        bibleServiceImpl: BibleServiceImpl
    ): BibleService

    @Binds
    @Singleton
    abstract fun bindsBoardService(
        boardService: BoardServiceImpl
    ): BoardService


}