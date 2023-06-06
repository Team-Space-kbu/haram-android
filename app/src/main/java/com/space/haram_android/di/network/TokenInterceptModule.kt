package com.space.haram_android.di.network

import com.space.haram_android.common.token.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.Request
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class TokenInterceptModule {

    @Provides
    @Singleton
    @Named("addHeader")
    fun provideInterceptor(
        tokenManager: TokenManager
    ): Interceptor {
        return Interceptor { chain ->
            val request: Request = chain.request()
            return@Interceptor chain.proceed(
                request.newBuilder()
                    .addHeader("accessToken", tokenManager.getAccessToken()!!)
                    .build()
            )
        }
    }

}