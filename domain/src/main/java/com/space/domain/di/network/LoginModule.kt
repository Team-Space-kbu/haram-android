package com.space.domain.di.network

import com.space.domain.di.network.DefaultNetworkModule.Companion.BASE_URL
import com.space.domain.service.token.TokenManager
import com.space.shared.annotation.SpaceLoginModule
import com.space.shared.annotation.TokenAddHeader
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
class LoginModule {

    @Provides
    @Singleton
    fun provideInterceptor(
        tokenManager: TokenManager
    ): Interceptor {
        return Interceptor { chain ->
            val request: Request = chain.request()
            return@Interceptor chain.proceed(
                request.newBuilder()
                    .addHeader("User-Agent", System.getProperty("http.agent") as String)
                    .build()
            )
        }
    }

    @Provides
    @Singleton
    @SpaceLoginModule
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        Interceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(Interceptor)
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @SpaceLoginModule
    fun provideRetrofit(
        @SpaceLoginModule okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

}
