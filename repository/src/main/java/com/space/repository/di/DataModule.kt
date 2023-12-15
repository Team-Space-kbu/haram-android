package com.space.repository.di

import com.space.repository.service.impl.AuthServiceImpl
import com.space.repository.service.impl.BibleServiceImpl
import com.space.repository.service.inf.BookService
import com.space.repository.service.inf.ChapelService
import com.space.repository.service.inf.HomeService
import com.space.repository.service.inf.MileageService
import com.space.repository.service.inf.PartnersService
import com.space.repository.service.impl.BookServiceImpl
import com.space.repository.service.impl.ChapelServiceImpl
import com.space.repository.service.impl.HomeServiceImpl
import com.space.repository.service.impl.MileageServiceImpl
import com.space.repository.service.impl.PartnersServiceImpl
import com.space.repository.service.inf.AuthService
import com.space.repository.service.inf.BibleService
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


}