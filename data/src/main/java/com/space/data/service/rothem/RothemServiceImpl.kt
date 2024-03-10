package com.space.data.service.rothem

import com.space.data.rest.RothemApi
import com.space.shared.common.exception.ExistReservation
import com.space.shared.data.rothem.Reservation
import com.space.shared.data.rothem.RoomDetail
import com.space.shared.data.rothem.RoomReservation
import com.space.shared.data.rothem.Rothem
import com.space.shared.data.rothem.RothemNotice
import com.space.shared.model.ReservationsModel
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
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
            try {
                rothemApi.postRoomReservations(roomSeq, reservationsModel)
                true
            }catch (e: HttpException){
                throw ExistReservation("예약 정보가 이미 존재합니다.")
            }

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