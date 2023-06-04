package com.space.haram_android.common.module.network

import com.space.haram_android.service.LoginService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoginNetworkModule {

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LoginRetrofit

    @Provides
    @Singleton
    @LoginRetrofit
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @LoginRetrofit
    fun provideRetrofit(
         @LoginRetrofit okHttpClient: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(NetworkModule.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(gsonConverterFactory)
        .build()

    @Singleton
    @Provides
    fun provideLoginService(
        @LoginRetrofit retrofit: Retrofit
    ): LoginService {
        return retrofit.create(LoginService::class.java)
    }

}
