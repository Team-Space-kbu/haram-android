package com.space.domain.di.network

import com.space.shared.annotation.IntranetModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class IntranetModule {

    @Provides
    @Singleton
    @IntranetModule
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }


    @Provides
    @Singleton
    @IntranetModule
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    @IntranetModule
    fun provideScalarsConverterFactory(): ScalarsConverterFactory {
        return ScalarsConverterFactory.create()
    }

    @Provides
    @Singleton
    @IntranetModule
    fun provideRetrofit(
        @IntranetModule okHttpClient: OkHttpClient,
        @IntranetModule gsonConverterFactory: GsonConverterFactory,
        @IntranetModule scalarsConverterFactory: ScalarsConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl("https://m.bible.ac.kr/")
        .client(okHttpClient)
        .addConverterFactory(scalarsConverterFactory)
        .addConverterFactory(gsonConverterFactory)
        .build()

}
