package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.course.Course
import com.space.shared.data.course.CourseHome
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CourseApi {
    @GET("/v2/intranet/class")
    suspend fun getClassRoom(): SpaceBody<List<String>>

    @GET("/v2/intranet/class/{classRoom}")
    suspend fun getClassRoomBuilding(
        @Path(value = "classRoom") classRoom: String
    ): SpaceBody<List<String>>

    @GET("/v2/intranet/class/detail")
    suspend fun getClassRoomDetail(
        @Query(value = "classRoom") course: String,
    ): SpaceBody<List<Course>>


    @GET("/v2/intranet/course")
    suspend fun getCourse(): SpaceBody<List<CourseHome>>

    @GET("/v2/intranet/course/{course}")
    suspend fun getCourseDetail(
        @Query(value = "course") course: String,
    ): SpaceBody<List<Course>>


}