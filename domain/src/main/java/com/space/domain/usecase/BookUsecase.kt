package com.space.domain.usecase

import com.space.shared.SpaceBody
import com.space.shared.result.ResultData
import com.space.shared.data.book.BookHome
import com.space.shared.data.book.BookDetail
import com.space.shared.data.book.BookKeep
import com.space.shared.data.book.BookSearch
import com.space.repository.api.BookApi
import com.space.domain.util.resHandling
import com.space.shared.exception.IndexOutException
import com.space.shared.exception.NotWorkNaver
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class BookUsecase @Inject constructor(
    private val bookApi: BookApi,
) {
    suspend fun getBookHomeInfo(): ResultData<BookHome> {
        TODO()
//        val req = runBlocking {
//            bookApi.getBookHome()
//        }
//        return isReqData(req)
    }

    suspend fun getBookSearchList(
        searchText: String,
        index: Int?
    ): ResultData<BookSearch> {
        TODO()
//        val req = runBlocking {
//            bookApi.getBokSearch(searchText, index)
//        }
//        return isReqData(req)
    }

    suspend fun getBookDetailInfo(infoPath: Int): ResultData<BookDetail> {
        TODO()
//        val req = runBlocking {
//            bookApi.getBookDetailInfo(infoPath)
//        }
//        return isReqData(req)
    }

    suspend fun getBookDetailKeep(infoPath: Int): ResultData<BookKeep> {
//        return runBlocking {
//            bookApi.getBookDetailKeep(infoPath).
//        }
        TODO()
    }

//    private fun <T> isReqData(response: SpaceBody<BookDetail>): ResultData<T> {
//        response.run {
//            if (response.isSuccessful) {
//                return when (body()!!.code) {
//                    "PA01", "LIB07" -> {
//                        ResultData.Success(body()!!.data)
//                    }
//
//                    "LIB04" -> {
//                        ResultData.Error(NullPointerException())
//                    }
//
//                    "LIB01", "LIB02", "LIB03" -> {
//                        ResultData.Error(NotWorkNaver("서버의 API 사용이 초과되었습니다."));
//                    }
//
//                    else -> {
//                        ResultData.Error(IndexOutException("지정된 Index 범위를 벗어나거나, Index가 지정되지 않았습니다."))
//                    }
//                }
//            }
//            return resHandling(code())
//        }
//    }

}