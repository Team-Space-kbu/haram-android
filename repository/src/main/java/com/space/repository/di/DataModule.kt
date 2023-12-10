package com.space.repository.di

import com.space.repository.BookService
import com.space.repository.ChapelService
import com.space.repository.HomeService
import com.space.repository.MileageService
import com.space.repository.PartnersService
import com.space.repository.service.BookServiceImpl
import com.space.repository.service.ChapelServiceImpl
import com.space.repository.service.HomeServiceImpl
import com.space.repository.service.MileageServiceImpl
import com.space.repository.service.PartnersServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {

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



}