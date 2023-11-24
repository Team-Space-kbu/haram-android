package com.space.domain2.api

import com.space.data.ResponseBody
import com.space.data.model.LoginModel
import com.space.data.model.RefreshModel
import com.space.data.response.LoginRes
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import javax.inject.Singleton

interface SpaceAuthApi {

    @POST("/v1/auth/login")
    suspend fun getLogin(
        @Body request: LoginModel
    ): Response<ResponseBody<LoginRes>>

    @POST("/v1/auth/refresh")
    suspend fun updateAccessToken(
        @Header("Authorization") refreshToken: String?,
        @Body userId: RefreshModel
    ): Response<ResponseBody<LoginRes>>


}

@Module
@InstallIn(SingletonComponent::class)
class SpaceAuthApiModule {
    @Singleton
    @Provides
    fun provideHomeService(
        retrofit: Retrofit
    ): SpaceHomeApi {
        return retrofit.create(SpaceHomeApi::class.java)
    }

}