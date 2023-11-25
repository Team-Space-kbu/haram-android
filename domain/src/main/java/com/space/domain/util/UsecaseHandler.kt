package com.space.domain.util

import com.space.data.result.ResultData
import com.space.shared.exception.ExpirationTokenException
import com.space.shared.exception.InvalidTokenException
import com.space.shared.exception.UnauthorizedTokenException
import com.space.shared.exception.UnknownException


fun <T> resHandling(code: Int): ResultData<T> {
    return when (code) {
        400 ->
            ResultData.Unauthorized(UnsupportedOperationException("잘못된 요청입니다."))

        402 ->
            ResultData.Unauthorized(ExpirationTokenException("로그인 정보가 일치 하지 않아 만료되었습니다."))

        403 ->
            ResultData.Unauthorized(UnauthorizedTokenException("요청을 처리할 권한이 없습니다."))

        404 ->
            ResultData.Unauthorized(UnsupportedOperationException("잘못된 API 요청이 되거나 값 요청이 잘못되었습니다."))

        418 ->
            ResultData.Unauthorized(InvalidTokenException("발급된 토큰이 문제가 있어 처리할 수 없습니다."))

        in 500..599 ->
            ResultData.Error(UnknownException("서버에서 오류가 발생했습니다."))

        else ->
            ResultData.Error(UnknownError("What..??"))

    }

}