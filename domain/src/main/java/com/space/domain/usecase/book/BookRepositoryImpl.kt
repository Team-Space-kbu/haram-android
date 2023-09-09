package com.space.domain.usecase.book

import com.space.data.ResponseBody
import com.space.data.ResultData
import com.space.data.response.book.BookHomeReq
import com.space.data.response.book.data.BookDetailInfo
import com.space.data.response.book.data.BookKeepInfo
import com.space.data.response.book.data.SearchResultModel
import com.space.domain.service.BookService
import com.space.shared.exception.IndexOutException
import com.space.shared.exception.InvalidTokenException
import com.space.shared.exception.UnknownException
import kotlinx.coroutines.runBlocking
import retrofit2.Response
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookService: BookService
) : BookRepository {
    override suspend fun getBookHomeInfo(): ResultData<BookHomeReq> {
        val req = runBlocking {
            bookService.getBookHome()
        }
        return isReqData(req)
    }

    override suspend fun getBookSearchList(searchText: String): ResultData<List<SearchResultModel>> {
        val req = runBlocking {
            bookService.getBokSearch(searchText)
        }
        return isReqData(req)
    }

    override suspend fun getBookDetailInfo(infoPath: Int): ResultData<BookDetailInfo> {
        val req = runBlocking {
            bookService.getBookDetailInfo(infoPath)
        }
        return isReqData(req)
    }

    override suspend fun getBookDetailKeep(infoPath: Int): ResultData<List<BookKeepInfo>> {
        val req = runBlocking {
            bookService.getBookDetailKeep(infoPath)
        }
        return isReqData(req)
    }

    private fun <T> isReqData(req: Response<ResponseBody<T>>): ResultData<T> {
        if (!req.isSuccessful) {
            return ResultData.Error(UnknownException("알 수 없는 오류 발생"))
        }
        return req.run {
            when (code()) {
                200 -> {
                    if (body()!!.code == "PA01") {
                        return ResultData.Success(body()!!.data)
                    }
                    if (body()!!.code == "LIB07"){
                        return ResultData.Success(body()!!.data)
                    }
                    return ResultData.Unauthorized(IndexOutException())
                }
                403 -> ResultData.Error(InvalidTokenException("토큰 정보가 유요하지 않습니다."))
                else -> {
                    ResultData.Unauthorized(IndexOutException())
                }
            }

        }
    }

}