package com.space.domain.usecase.book

import com.space.data.ResultData
import com.space.data.res.book.BookHomeReq
import com.space.data.res.book.data.BookDetailInfo
import com.space.data.res.book.data.BookKeepInfo
import com.space.data.res.book.data.SearchResultModel


interface BookRepository {
    suspend fun getBookHomeInfo(): ResultData<BookHomeReq>

    suspend fun getBookSearchList(searchText: String): ResultData<List<SearchResultModel>>

    suspend fun getBookDetailInfo(infoPath: Int): ResultData<BookDetailInfo>

    suspend fun getBookDetailKeep(infoPath: Int): ResultData<List<BookKeepInfo>>

}