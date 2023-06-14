package com.space.haram_android.repository.home

import android.util.Log
import com.space.haram_android.common.data.ResultData
import com.space.haram_android.repository.ResponseBody
import com.space.haram_android.common.data.response.home.HomeRes
import com.space.haram_android.common.exception.InvalidTokenException
import com.space.haram_android.service.HomeService
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
        } catch (e: Exception) {
            Log.d("HomeService", "Exception 에러${e.message}")
            return ResultData.Error(e)
        } catch (e: IOException) {
            Log.d("HomeService", "IO 에러${e.message}")
            return ResultData.Error(e)
        } catch (e: NullPointerException) {
            Log.d("HomeService", "NullPointer 에러${e.message}")
            return ResultData.Error(e)
        }
    }
}