package com.space.data.service.rothem

import com.space.shared.data.rothem.Reservation
import com.space.shared.data.rothem.RoomDetail
import com.space.shared.data.rothem.RoomReservation
import com.space.shared.data.rothem.Rothem
import com.space.shared.model.DeleteReservations
import com.space.shared.model.ReservationsModel


interface RothemService {
    suspend fun getRothemHome(userId: String): Rothem

    suspend fun getRoomDetail(roomSeq: String): RoomDetail

    suspend fun getRoomReservations(roomSeq: String): RoomReservation

    suspend fun postRoomReservations(
        roomSeq: String,
        reservationsModel: ReservationsModel
    ): Boolean


    suspend fun getReservations(userId: String): Reservation

    suspend fun deleteReservations(deleteReservations: DeleteReservations):Boolean
}