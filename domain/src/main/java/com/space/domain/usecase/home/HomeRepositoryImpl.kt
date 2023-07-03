package com.space.domain.usecase.home

import android.util.Log
import com.space.data.ResponseBody
import com.space.data.ResultData
import com.space.data.response.home.HomeRes
import com.space.domain.service.HomeService
import com.space.shared.exception.InvalidTokenException
import kotlinx.coroutines.runBlocking
import java.io.IOException
import java.lang.Exception
import java.lang.NullPointerException
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeService: HomeService
) : HomeRepository {


    override fun getHome(): ResultData<ResponseBody<HomeRes>> {
        try {
            val res = runBlocking {
                homeService.getHome()
            }
            return when (res.code()) {
                200 -> ResultData.Success(res.body()!!)
                403 -> ResultData.Unauthorized(InvalidTokenException("Token refresh failed"))
                else -> ResultData.Error(Exception("알 수 없는 오류"))
            }
        } catch (e: IOException) {
            Log.d("HomeService", "IO 에러${e.message}")
            return ResultData.Error(e)
        } catch (e: NullPointerException) {
            Log.d("HomeService", "NullPointer 에러${e.message}")
            return ResultData.Error(e)
        } catch (e: Exception) {
            Log.d("HomeService", "Exception 에러${e.message}")
            return ResultData.Error(e)
        }
    }
}