package com.space.data.di.retrofit

import com.space.data.di.retrofit.DefaultNetworkModule.Companion.BASE_URL
import com.space.shared.common.annotation.SpaceLoginModule
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
    fun provideInterceptor(): Interceptor {
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
        interceptor: Interceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
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
