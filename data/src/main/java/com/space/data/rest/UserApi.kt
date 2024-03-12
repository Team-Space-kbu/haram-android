package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.auth.User
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApi {

    @GET("/v1/users/{userId}")
    suspend fun getUser(
        @Path("userId") userId: String
    ): SpaceBody<User>
}