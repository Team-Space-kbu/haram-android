package com.space.haram_android.di.network

import android.content.Context
import com.space.haram_android.common.annotation.SpaceLoginModule
import com.space.haram_android.common.annotation.TokenAddHeader
import com.space.haram_android.common.token.AuthManager
import com.space.haram_android.common.token.TokenManager
import com.space.haram_android.di.network.common.AuthAuthenticator
import com.space.haram_android.di.network.common.DefaultNetworkModule.Companion.BASE_URL
import com.space.haram_android.di.network.common.DefaultNetworkModule.Companion.CACHE_SIZE_BYTES
import com.space.haram_android.di.network.common.DefaultNetworkModule.Companion.CONNECTION_TIMEOUT
import com.space.haram_android.di.network.common.DefaultNetworkModule.Companion.READ_TIMEOUT
import com.space.haram_android.di.network.common.DefaultNetworkModule.Companion.WRITE_TIMEOUT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
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
                    .addHeader("Authorization", "Bearer ${tokenManager.getAccessToken()}")
                    .build()
            )
        }
    }

    @Provides
    @Singleton
    internal fun provideCache(@ApplicationContext context: Context): Cache {
        val httpCacheDirectory = File(context.cacheDir.absolutePath, "HttpCache")
        return Cache(httpCacheDirectory, CACHE_SIZE_BYTES)
    }

    @Singleton
    @Provides
    fun provideAuthAuthenticator(
        tokenManager: TokenManager,
        authManager: AuthManager,
        @SpaceLoginModule retrofit: Retrofit
    ): AuthAuthenticator =
        AuthAuthenticator(tokenManager, authManager, retrofit)

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @TokenAddHeader Interceptor: Interceptor,
        authAuthenticator: AuthAuthenticator,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(Interceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .authenticator(authAuthenticator)
            .connectTimeout(CONNECTION_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(READ_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(WRITE_TIMEOUT.toLong(), TimeUnit.SECONDS)
            .cache(cache)
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