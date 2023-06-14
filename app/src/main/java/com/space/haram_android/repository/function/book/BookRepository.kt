package com.space.haram_android.repository.function.book

import com.space.haram_android.common.data.ResultData
import com.space.haram_android.common.data.response.book.BookDetailReq
import com.space.haram_android.common.data.response.book.BookHomeReq
import com.space.haram_android.common.data.response.book.data.SearchResultModel


interface BookRepository {
    suspend fun getBookHomeInfo(): ResultData<BookHomeReq>

    suspend fun getBookSearchList(searchText: String): ResultData<List<SearchResultModel>>

    suspend fun getBookDetailInfo(infoPath: String): ResultData<BookDetailReq>

}