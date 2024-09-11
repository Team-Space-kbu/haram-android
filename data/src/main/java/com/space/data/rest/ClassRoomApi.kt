package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.class_room.Course
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ClassRoomApi {
    @GET("/v2/intranet/class")
    suspend fun getClassRoom(
    ): SpaceBody<List<String>>

    @GET("/v2/intranet/class/{classRoom}")
    suspend fun getClassRoomBuilding(
        @Path(value = "classRoom") classRoom: String
    ): SpaceBody<List<String>>

    @GET("/v2/intranet/class/detail")
    suspend fun getClassRoomDetail(
        @Query(value = "classRoom") course: String,
    ): SpaceBody<List<Course>>


}