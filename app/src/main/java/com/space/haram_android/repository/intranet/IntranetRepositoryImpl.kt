package com.space.haram_android.repository.intranet

import android.util.Log
import com.space.haram_android.common.data.ResultData
import com.space.haram_android.common.data.model.LoginIntranetModel
import com.space.haram_android.common.data.response.IntranetTokenRes
import com.space.haram_android.common.exception.InvalidIntranetException
import com.space.haram_android.common.exception.InvalidTokenException
import com.space.haram_android.repository.ResponseBody
import com.space.haram_android.service.IntranetService
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import retrofit2.Response
import java.io.IOException
import java.lang.Exception
import java.lang.NullPointerException
import javax.inject.Inject

class IntranetRepositoryImpl @Inject constructor(
    private val intranetService: IntranetService
) : IntranetRepository {

    override suspend fun getIntranetLogin(
        intranetModel: LoginIntranetModel,
        intranetTokenRes: IntranetTokenRes
    ): ResultData<Boolean> {
        val cookie =
            "XSRF-TOKEN=${intranetTokenRes.xsrf_token}; laravel_session=${intranetTokenRes.laravel_session}"
        val res = runBlocking {
            intranetService.getLogin(cookie, intranetModel)
        }
        val document: Document = Jsoup.parse(res.body().toString())
        return if (document.select("#container > article > h2").text() == "오늘의 강의 목록") {
            ResultData.Success(true)
        } else {
            ResultData.Error(InvalidIntranetException("로그인 정보가 잘못되었습니다."))
        }

    }

    override suspend fun getIntranetToken(): ResultData<ResponseBody<IntranetTokenRes>> {
        try {
            val res = runBlocking {
                intranetService.getTokenInfo()
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