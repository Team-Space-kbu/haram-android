package com.space.pdf_viewer.di

import android.content.Context
import com.space.navigator.view.NavigatorPdf
import com.space.pdf_viewer.ViewerActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigatorPdfModule {
    @Binds
    abstract fun providerPdfNavigator(
        impl: NavigatorPdfImpl
    ): NavigatorPdf

}

internal class NavigatorPdfImpl @Inject constructor() : NavigatorPdf {
    override fun openView(context: Context, pdf: String) {
        ViewerActivity.open(context, pdf)
    }
}