package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.auth.User
import com.space.shared.model.PermutePasswordModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserApi {

    @GET("/v1/users/{userId}")
    suspend fun getUser(
        @Path("userId") userId: String
    ): SpaceBody<User>


    @PUT("/v1/users/{userId}/passwords")
    suspend fun putPassword(
        @Path("userId") userId: String,
        @Body password: PermutePasswordModel
    ): SpaceBody<Boolean>


}