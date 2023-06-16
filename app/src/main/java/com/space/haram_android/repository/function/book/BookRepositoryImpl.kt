package com.space.haram_android.repository.function.book

import com.space.haram_android.common.data.ResultData
import com.space.haram_android.common.data.response.book.BookDetailReq
import com.space.haram_android.common.data.response.book.BookHomeReq
import com.space.haram_android.common.data.response.book.data.SearchResultModel
import com.space.haram_android.common.data.response.intranet.IntranetTokenRes
import com.space.haram_android.common.exception.IndexOutException
import com.space.haram_android.common.exception.InvalidIntranetException
import com.space.haram_android.common.exception.InvalidTokenException
import com.space.haram_android.common.exception.UnknownException
import com.space.haram_android.repository.ResponseBody
import com.space.haram_android.service.BookService
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

    override suspend fun getBookDetailInfo(infoPath: String): ResultData<BookDetailReq> {
        val req = runBlocking {
            bookService.getBookDetail(infoPath)
        }
        return isReqData(req)
    }

    private fun <T> isReqData(req: Response<ResponseBody<T>>): ResultData<T> {
        if (!req.isSuccessful) {
            return ResultData.Error(UnknownException("알 수 없는 오류 발생"))
        }
        return when (req.code()) {
            200 -> {
                if (req.body() != null) {
                    ResultData.Success(req.body()?.data!!)
                } else {
                    ResultData.Error(IndexOutException())
                }
            }

            403 -> ResultData.Error(InvalidTokenException("토큰 정보가 "))
            else -> {
                ResultData.Unauthorized(IndexOutException())
            }
        }
    }


}