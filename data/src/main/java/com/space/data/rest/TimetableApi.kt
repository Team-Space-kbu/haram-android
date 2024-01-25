package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.timetable.Timetable
import retrofit2.http.GET

interface TimetableApi {
    @GET("/v2/intranet/timetable")
    suspend fun getTimetable(): SpaceBody<List<Timetable>>
}