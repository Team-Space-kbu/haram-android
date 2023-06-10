package com.space.haram_android.di.network

import com.space.haram_android.common.annotation.SpaceLoginModule
import com.space.haram_android.common.annotation.TokenAddHeader
import com.space.haram_android.common.token.TokenManager
import com.space.haram_android.di.network.common.AuthAuthenticator
import com.space.haram_android.di.network.common.DefaultNetworkModule.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class SpaceModule {

    @Provides
    @Singleton
    @TokenAddHeader
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


    @Singleton
    @Provides
    fun provideAuthAuthenticator(
        tokenManager: TokenManager,
        @SpaceLoginModule retrofit: Retrofit
    ): AuthAuthenticator =
        AuthAuthenticator(tokenManager, retrofit)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @TokenAddHeader Interceptor: Interceptor,
        authAuthenticator: AuthAuthenticator,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(Interceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .authenticator(authAuthenticator)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

}