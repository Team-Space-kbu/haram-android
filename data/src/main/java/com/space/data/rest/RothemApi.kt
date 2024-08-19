package com.space.data.rest

import com.space.shared.SpaceBody
import com.space.shared.data.rothem.Reservation
import com.space.shared.data.rothem.RoomDetail
import com.space.shared.data.rothem.RoomReservation
import com.space.shared.data.rothem.Rothem
import com.space.shared.model.DeleteReservations
import com.space.shared.model.ReservationsModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.HTTP
import retrofit2.http.POST
import retrofit2.http.Path

interface RothemApi {
    @GET("/v1/rothem/main/{userId}")
    suspend fun getRothemHome(
        @Path(value = "userId") userId: String
    ): SpaceBody<Rothem>

    @GET("/v1/rothem/rooms/{roomSeq}")
    suspend fun getRoomDetail(
        @Path(value = "roomSeq") roomSeq: String
    ): SpaceBody<RoomDetail>

    @GET("/v1/rothem/rooms/{roomSeq}/reservations")
    suspend fun getRoomReservations(
        @Path(value = "roomSeq") roomSeq: String
    ): SpaceBody<RoomReservation>

    @POST("/v1/rothem/rooms/{roomSeq}/reservations")
    suspend fun postRoomReservations(
        @Path(value = "roomSeq") roomSeq: String,
        @Body reservationsModel: ReservationsModel
    ): SpaceBody<String>

    @GET("/v1/rothem/reservations/{userId}")
    suspend fun getReservations(
        @Path(value = "userId") userId: String,
    ): SpaceBody<Reservation>

    @HTTP(method = "DELETE", path="/v1/rothem/reservations", hasBody = true)
    suspend fun deleteReservations(
        @Body deleteReservations: DeleteReservations
    ): SpaceBody<String>
}