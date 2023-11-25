package com.space.domain.usecase

import android.util.Log
import com.google.gson.stream.MalformedJsonException
import com.space.data.SpaceBody
import com.space.data.result.ResultData
import com.space.data.model.LoginIntranetModel
import com.space.data.response.intranet.IntranetTokenRes
import com.space.domain.service.IntranetLoginService
import com.space.domain.service.IntranetService
import com.space.repository.token.IntranetManager
import com.space.shared.exception.InvalidIntranetException
import com.space.shared.exception.InvalidTokenException
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.lang.Exception
import java.lang.NullPointerException
import javax.inject.Inject

class IntranetUsecase @Inject constructor(
    private val intranetLoginService: IntranetLoginService,
    private val intranetService: IntranetService,
    private val intranetManager: IntranetManager
)  {

    fun getIntranetTokenData(): IntranetTokenRes {
        return intranetManager.getIntranetToken()
    }

    fun getIntranetIdModel(): LoginIntranetModel {
        return intranetManager.getIntranetIdModel()
    }

    fun saveIntranetToken(intranetTokenRes: IntranetTokenRes) {
        return intranetManager.saveIntranetToken(intranetTokenRes)
    }

    fun saveIntranetModel(intranetModel: LoginIntranetModel) {
        return intranetManager.saveIntranetModel(intranetModel)
    }

    suspend fun isInvalidToken(intranetTokenRes: IntranetTokenRes): ResultData<Boolean> {

        try {
            val cookie =
                "XSRF-TOKEN=${intranetTokenRes.xsrf_token}; laravel_session=${intranetTokenRes.laravel_session}"
            val res = runBlocking {
                intranetLoginService.getHome(cookie)
            }
            return isTokenInfo(res)
        } catch (e: MalformedJsonException) {
            throw MalformedJsonException(e.message.toString())
        } catch (e: Exception) {
            throw Exception("변환 과정중 오류가 발생했습니다. = ${e.message}")
        }
    }

    suspend fun getIntranetLogin(
        intranetModel: LoginIntranetModel,
        intranetTokenRes: IntranetTokenRes
    ): ResultData<Boolean> {
        val cookie =
            "XSRF-TOKEN=${intranetTokenRes.xsrf_token}; laravel_session=${intranetTokenRes.laravel_session}"
        try {
            val res = runBlocking {
                intranetLoginService.getLogin(cookie, intranetModel)
            }
            return isTokenInfo(res)
        } catch (e: MalformedJsonException) {
            throw MalformedJsonException(e.message.toString())
        } catch (e: Exception) {
            throw Exception("변환 과정중 오류가 발생했습니다. = ${e.message}")
        }

    }

    suspend fun getIntranetToken(): ResultData<SpaceBody<IntranetTokenRes>> {
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

    private fun isTokenInfo(res: String): ResultData<Boolean> {
        val document: Document = Jsoup.parse(res)
        return if (document.select("#container > article > h2").text() == "오늘의 강의 목록") {
            Log.d("IntranetRepository","Token 정보 유효")
            ResultData.Success(true)
        } else {
            Log.d("IntranetRepository","Token 만료 및 잘못된 정보")
            ResultData.Error(InvalidIntranetException("로그인 정보가 잘못되었습니다."))
        }
    }
}