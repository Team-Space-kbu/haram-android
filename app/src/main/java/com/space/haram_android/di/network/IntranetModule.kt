package com.space.haram_android.di.network

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.space.haram_android.common.annotation.IntranetModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.jsoup.Jsoup
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit
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
