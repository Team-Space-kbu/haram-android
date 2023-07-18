package com.space.domain.usecase.function.book

import com.space.data.ResultData
import com.space.data.response.book.BookHomeReq
import com.space.data.response.book.data.BookDetailInfo
import com.space.data.response.book.data.BookKeepInfo
import com.space.data.response.book.data.SearchResultModel


interface BookRepository {
    suspend fun getBookHomeInfo(): ResultData<BookHomeReq>

    suspend fun getBookSearchList(searchText: String): ResultData<List<SearchResultModel>>

    suspend fun getBookDetailInfo(infoPath: Int): ResultData<BookDetailInfo>

    suspend fun getBookDetailKeep(infoPath: Int): ResultData<List<BookKeepInfo>>

}