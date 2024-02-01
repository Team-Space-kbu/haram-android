package com.space.data.service.rothem

import com.space.data.rest.RothemApi
import com.space.shared.data.rothem.Reservation
import com.space.shared.data.rothem.RoomDetail
import com.space.shared.data.rothem.RoomReservation
import com.space.shared.data.rothem.Rothem
import com.space.shared.data.rothem.RothemNotice
import com.space.shared.model.ReservationsModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class RothemServiceImpl @Inject constructor(
    private val rothemApi: RothemApi
) : RothemService {

    override suspend fun getRothemHome(userId: String): Rothem {
        return runBlocking {
            rothemApi.getRothemHome(userId).data
        }
    }

    override suspend fun getRoomDetail(roomSeq: String): RoomDetail {
        return runBlocking {
            rothemApi.getRoomDetail(roomSeq).data
        }
    }

    override suspend fun getRoomReservations(roomSeq: String): RoomReservation {
        return runBlocking {
            rothemApi.getRoomReservations(roomSeq).data
        }
    }

    override suspend fun postRoomReservations(
        roomSeq: String,
        reservationsModel: ReservationsModel
    ): Boolean {
        return runBlocking {
            rothemApi.postRoomReservations(roomSeq, reservationsModel)
            true
        }
    }

    override suspend fun getRothemNotice(noticeSeq: String): RothemNotice {
        return runBlocking {
            rothemApi.getRothemNotice(noticeSeq).data
        }
    }

    override suspend fun getReservations(userId: String): Reservation {
        return runBlocking {
            rothemApi.getReservations(userId).data
        }
    }
}