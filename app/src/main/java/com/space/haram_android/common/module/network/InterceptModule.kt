package com.space.haram_android.common.module.network

import android.content.SharedPreferences
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
class InterceptModule {


    @Provides
    @Singleton
    @Named("Interceptor")
    fun provideInterceptor(
        sharedPreferences: SharedPreferences
    ): Interceptor {
        return Interceptor { chain ->
            val request: Request = chain.request()
            val accessToken: String? = sharedPreferences.getString("accessToken", "")
            if (!accessToken.isNullOrBlank()) {
                request.newBuilder().addHeader("accessToken", accessToken)
            }
            chain.proceed(request)
        }
    }

    @Provides
    @Singleton
    @Named("TokenInterceptor")
    fun provideTokenInterceptor(
        sharedPreferences: SharedPreferences
    ): Interceptor {
        return Interceptor { chain ->
            val request: Request = chain.request()
//            val accessToken: String? = sharedPreferences.getString("accessToken", "")
//            if (!accessToken.isNullOrBlank()) {
//                request.newBuilder().addHeader("accessToken", accessToken)
//            }
            chain.proceed(request)
        }
    }


}