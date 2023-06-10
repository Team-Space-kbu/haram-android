package com.space.haram_android.di.network

import com.space.haram_android.common.annotation.IntranetModule
import com.space.haram_android.di.network.common.DefaultNetworkModule.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class IntranetModule {

    @Provides
    @Singleton
    @IntranetModule
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @IntranetModule
    fun provideRetrofit(
        @IntranetModule okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://m.bible.ac.kr/")
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

}
