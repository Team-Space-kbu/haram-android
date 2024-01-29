package com.space.data.di

import com.space.data.service.auth.AuthService
import com.space.data.service.auth.AuthServiceImpl
import com.space.data.service.login.LoginService
import com.space.data.service.partners.PartnersService
import com.space.data.service.login.LoginServiceImpl
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
import com.space.data.service.timetable.TimetableService
import com.space.data.service.timetable.TimetableServiceImpl
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
    abstract fun bindsLoginService(
        impl: LoginServiceImpl
    ): LoginService

    @Binds
    @Singleton
    abstract fun bindsHomeService(
        impl: HomeServiceImpl
    ): HomeService

    @Binds
    @Singleton
    abstract fun bindsBookService(
        impl: BookServiceImpl
    ): BookService

    @Binds
    @Singleton
    abstract fun bindsMileageService(
        impl: MileageServiceImpl
    ): MileageService

    @Binds
    @Singleton
    abstract fun bindsChapelService(
        impl: ChapelServiceImpl
    ): ChapelService

    @Binds
    @Singleton
    abstract fun bindsPartnersService(
        impl: PartnersServiceImpl
    ): PartnersService

    @Binds
    @Singleton
    abstract fun bindsBibleService(
        impl: BibleServiceImpl
    ): BibleService

    @Binds
    @Singleton
    abstract fun bindsBoardService(
        impl: BoardServiceImpl
    ): BoardService

    @Binds
    @Singleton
    abstract fun bindsTimetableService(
        impl: TimetableServiceImpl
    ): TimetableService


    @Binds
    @Singleton
    abstract fun bindAuthService(
        impl: AuthServiceImpl
    ): AuthService
}