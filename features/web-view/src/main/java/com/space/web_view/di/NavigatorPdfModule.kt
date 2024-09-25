package com.space.web_view.di

import android.content.Context
import com.space.navigator.view.NavigatorWebView
import com.space.web_view.WebViewActivity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject


@Module
@InstallIn(SingletonComponent::class)
internal abstract class NavigatorWebViewModule {
    @Binds
    abstract fun providerWebViewNavigator(
        impl: NavigatorWebViewImpl
    ): NavigatorWebView

}

internal class NavigatorWebViewImpl @Inject constructor() : NavigatorWebView {

    override fun openView(context: Context, webView: Pair<String, String>) {
        WebViewActivity.open(context, webView)
    }
}