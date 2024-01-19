package com.space.repository.di

import com.space.repository.service.auth.AuthServiceImpl
import com.space.repository.service.bible.BibleServiceImpl
import com.space.repository.service.book.BookService
import com.space.repository.service.chpael.ChapelService
import com.space.repository.service.home.HomeService
import com.space.repository.service.mileage.MileageService
import com.space.repository.service.partners.PartnersService
import com.space.repository.service.book.BookServiceImpl
import com.space.repository.service.chpael.ChapelServiceImpl
import com.space.repository.service.home.HomeServiceImpl
import com.space.repository.service.mileage.MileageServiceImpl
import com.space.repository.service.partners.PartnersServiceImpl
import com.space.repository.service.auth.AuthService
import com.space.repository.service.bible.BibleService
import com.space.repository.service.board.BoardService
import com.space.repository.service.board.BoardServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

    @Binds
    abstract fun bindsAuthService(
        authServiceImpl: AuthServiceImpl
    ): AuthService

    @Binds
    abstract fun bindsHomeService(
        homeService: HomeServiceImpl
    ): HomeService

    @Binds
    abstract fun bindsBookService(
        bookServiceImpl: BookServiceImpl
    ): BookService

    @Binds
    abstract fun bindsMileageService(
        mileageService: MileageServiceImpl
    ): MileageService

    @Binds
    abstract fun bindsChapelService(
        chapelService: ChapelServiceImpl
    ): ChapelService

    @Binds
    abstract fun bindsPartnersService(
        partnersServiceImpl: PartnersServiceImpl
    ): PartnersService

    @Binds
    abstract fun bindsBibleService(
        bibleServiceImpl: BibleServiceImpl
    ): BibleService

    @Binds
    abstract fun bindsBoardService(
        boardService: BoardServiceImpl
    ): BoardService


}