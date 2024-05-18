package com.space.data.service.timetable

import com.space.data.rest.TimetableApi
import com.space.shared.exception.NotFoundStudentIdException
import com.space.shared.data.timetable.Timetable
import kotlinx.coroutines.runBlocking
import retrofit2.HttpException
import timber.log.Timber
import javax.inject.Inject

internal class TimetableServiceImpl @Inject constructor(
    private val timetableApi: TimetableApi
) : TimetableService {
    override suspend fun getTimetable(): List<Timetable> {
        return runBlocking {
            try {
                timetableApi.getTimetable().data
            } catch (e: HttpException) {
                if (e.code() == 460) {
                    Timber.i(e.message())
                    throw com.space.shared.exception.NotFoundStudentIdException("Student information cannot be obtained from the server.")
                }
                throw e
            }
        }
    }
}