package com.space.haram_android.usecase.intranet

import android.util.Log
import com.google.gson.stream.MalformedJsonException
import com.space.haram_android.common.data.ResultData
import com.space.haram_android.common.data.model.LoginIntranetModel
import com.space.haram_android.common.data.response.intranet.IntranetTokenRes
import com.space.haram_android.common.exception.InvalidIntranetException
import com.space.haram_android.common.exception.InvalidTokenException
import com.space.haram_android.common.token.IntranetManager
import com.space.haram_android.usecase.ResponseBody
import com.space.haram_android.service.IntranetLoginService
import com.space.haram_android.service.IntranetService
import kotlinx.coroutines.runBlocking
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.lang.Exception
import java.lang.NullPointerException
import javax.inject.Inject

class IntranetRepositoryImpl @Inject constructor(
    private val intranetLoginService: IntranetLoginService,
    private val intranetService: IntranetService,
    private val intranetManager: IntranetManager
) : IntranetRepository {

    override fun getIntranetTokenData(): IntranetTokenRes {
        return intranetManager.getIntranetToken()
    }

    override fun getIntranetIdModel(): LoginIntranetModel {
        return intranetManager.getIntranetIdModel()
    }

    override fun saveIntranetToken(intranetTokenRes: IntranetTokenRes) {
        return intranetManager.saveIntranetToken(intranetTokenRes)
    }

    override fun saveIntranetModel(intranetModel: LoginIntranetModel) {
        return intranetManager.saveIntranetModel(intranetModel)
    }

    override suspend fun isInvalidToken(intranetTokenRes: IntranetTokenRes): ResultData<Boolean> {
        val cookie =
            "XSRF-TOKEN=${intranetTokenRes.xsrf_token}; laravel_session=${intranetTokenRes.laravel_session}"
        try {
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

    override suspend fun getIntranetLogin(
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