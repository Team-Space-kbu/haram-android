package com.space.domain.usecase.function.book

import com.space.data.ResultData
import com.space.data.response.book.BookDetailReq
import com.space.data.response.book.BookHomeReq
import com.space.data.response.book.data.SearchResultModel


interface BookRepository {
    suspend fun getBookHomeInfo(): ResultData<BookHomeReq>

    suspend fun getBookSearchList(searchText: String): ResultData<List<SearchResultModel>>

    suspend fun getBookDetailInfo(infoPath: String): ResultData<BookDetailReq>

}