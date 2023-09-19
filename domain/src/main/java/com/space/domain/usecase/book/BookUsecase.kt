package com.space.domain.usecase.book

import com.space.data.ResultData
import com.space.data.res.book.BookHomeReq
import com.space.data.res.book.BookDetailInfo
import com.space.data.res.book.BookDetailKeep
import com.space.data.res.book.BookSearchReq

interface BookUsecase {
    suspend fun getBookHomeInfo(): ResultData<BookHomeReq>

    suspend fun getBookSearchList(searchText: String, index: Int?): ResultData<BookSearchReq>

    suspend fun getBookDetailInfo(infoPath: Int): ResultData<BookDetailInfo>

    suspend fun getBookDetailKeep(infoPath: Int): ResultData<BookDetailKeep>

}