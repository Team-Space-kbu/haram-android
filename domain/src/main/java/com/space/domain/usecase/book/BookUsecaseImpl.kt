package com.space.domain.usecase.book

import com.space.data.ResponseBody
import com.space.data.ResultData
import com.space.data.res.book.BookHomeReq
import com.space.data.res.book.BookDetailInfo
import com.space.data.res.book.BookDetailKeep
import com.space.data.res.book.BookSearchReq
import com.space.domain.service.BookService
import com.space.domain.util.UsecaseHandler
import com.space.shared.exception.IndexOutException
import com.space.shared.exception.NotWorkNaver
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import javax.inject.Inject

class BookUsecaseImpl @Inject constructor(
    private val bookService: BookService,
    private val usecaseHandler: UsecaseHandler
) : BookUsecase {
    override suspend fun getBookHomeInfo(): ResultData<BookHomeReq> {
        val req = runBlocking {
            bookService.getBookHome()
        }
        return isReqData(req)
    }

    override suspend fun getBookSearchList(
        searchText: String,
        index: Int?
    ): ResultData<BookSearchReq> {
        val req = runBlocking {
            bookService.getBokSearch(searchText, index)
        }
        return isReqData(req)
    }

    override suspend fun getBookDetailInfo(infoPath: Int): ResultData<BookDetailInfo> {
        val req = runBlocking {
            bookService.getBookDetailInfo(infoPath)
        }
        return isReqData(req)
    }

    override suspend fun getBookDetailKeep(infoPath: Int): ResultData<BookDetailKeep> {
        val req = runBlocking {
            bookService.getBookDetailKeep(infoPath)
        }
        return isReqData(req)
    }

    private fun <T> isReqData(response: Response<ResponseBody<T>>): ResultData<T> {
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
            return usecaseHandler.resHandling(code())
        }
    }

}