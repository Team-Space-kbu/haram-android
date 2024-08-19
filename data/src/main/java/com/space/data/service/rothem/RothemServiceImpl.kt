package com.space.data.service.rothem

import com.google.gson.Gson
import com.space.data.rest.RothemApi
import com.space.shared.SpaceBody
import com.space.shared.data.rothem.Reservation
import com.space.shared.data.rothem.RoomDetail
import com.space.shared.data.rothem.RoomReservation
import com.space.shared.data.rothem.Rothem
import com.space.shared.model.DeleteReservations
import com.space.shared.model.ReservationsModel
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import javax.inject.Inject


internal class RothemServiceImpl @Inject constructor(
    private val rothemApi: RothemApi
) : RothemService {

    private val gson = Gson()

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
            } catch (e: HttpException) {
                val responseBody = e.response()?.errorBody()?.string()
                val errorObject = gson.fromJson(responseBody, SpaceBody::class.java)
                when (errorObject.code) {
                    "RT08" -> {
                        throw com.space.shared.exception.ExistReservationException("예약 정보가 이미 존재합니다.")
                    }

                    else -> {
                        throw e
                    }
                }
            }

        }
    }


    override suspend fun getReservations(userId: String): Reservation {
        return runBlocking {
            rothemApi.getReservations(userId).data
        }
    }

    override suspend fun deleteReservations(deleteReservations: DeleteReservations): Boolean {
        return runBlocking {
            rothemApi.deleteReservations(deleteReservations)
            true
        }
    }
}