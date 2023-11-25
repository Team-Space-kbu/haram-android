package com.space.domain.usecase

import com.space.data.SpaceBody
import com.space.data.result.ResultData
import com.space.data.response.book.BookHomeReq
import com.space.data.response.book.BookDetailInfo
import com.space.data.response.book.BookDetailKeep
import com.space.data.response.book.BookSearchReq
import com.space.domain.service.BookService
import com.space.domain.util.resHandling
import com.space.shared.exception.IndexOutException
import com.space.shared.exception.NotWorkNaver
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import javax.inject.Inject

class BookUsecase @Inject constructor(
    private val bookService: BookService,
) {
    suspend fun getBookHomeInfo(): ResultData<BookHomeReq> {
        val req = runBlocking {
            bookService.getBookHome()
        }
        return isReqData(req)
    }

    suspend fun getBookSearchList(
        searchText: String,
        index: Int?
    ): ResultData<BookSearchReq> {
        val req = runBlocking {
            bookService.getBokSearch(searchText, index)
        }
        return isReqData(req)
    }

    suspend fun getBookDetailInfo(infoPath: Int): ResultData<BookDetailInfo> {
        val req = runBlocking {
            bookService.getBookDetailInfo(infoPath)
        }
        return isReqData(req)
    }

    suspend fun getBookDetailKeep(infoPath: Int): ResultData<BookDetailKeep> {
        val req = runBlocking {
            bookService.getBookDetailKeep(infoPath)
        }
        return isReqData(req)
    }

    private fun <T> isReqData(response: Response<SpaceBody<T>>): ResultData<T> {
        response.run {
            if (response.isSuccessful) {
                return when (body()!!.code) {
                    "PA01", "LIB07" -> {
                        ResultData.Success(body()!!.data)
                    }

                    "LIB04" -> {
                        ResultData.Error(NullPointerException())
                    }

                    "LIB01", "LIB02", "LIB03" -> {
                        ResultData.Error(NotWorkNaver("서버의 API 사용이 초과되었습니다."));
                    }

                    else -> {
                        ResultData.Error(IndexOutException("지정된 Index 범위를 벗어나거나, Index가 지정되지 않았습니다."))
                    }
                }
            }
            return resHandling(code())
        }
    }

}