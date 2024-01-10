package com.space.repository.di.retrofit

import android.content.Context
import com.space.repository.di.retrofit.DefaultNetworkModule.Companion.BASE_URL
import com.space.repository.di.retrofit.DefaultNetworkModule.Companion.CACHE_SIZE_BYTES
import com.space.repository.di.retrofit.DefaultNetworkModule.Companion.CONNECTION_TIMEOUT
import com.space.repository.di.retrofit.DefaultNetworkModule.Companion.READ_TIMEOUT
import com.space.repository.di.retrofit.DefaultNetworkModule.Companion.WRITE_TIMEOUT
import com.space.repository.di.token.AuthManager
import com.space.repository.di.token.TokenManager
import com.space.repository.service.inf.AuthService
import com.space.shared.common.annotation.SpaceLoginModule
import com.space.shared.common.annotation.TokenAddHeader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Authenticator
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
                    .addHeader("User-Agent", System.getProperty("http.agent") as String)
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

    @Provides
    @Singleton
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @TokenAddHeader interceptor: Interceptor,
        authAuthenticator: Authenticator,
        cache: Cache
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
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