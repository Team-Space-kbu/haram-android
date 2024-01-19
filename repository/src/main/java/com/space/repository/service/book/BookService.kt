package com.space.repository.service.book

import com.space.shared.data.book.BookDetailInfo
import com.space.shared.data.book.BookEtc
import com.space.shared.data.book.BookHome
import com.space.shared.data.book.BookSearch

interface BookService {
    suspend fun getBookHome(): BookHome

    suspend fun getBookSearch(
        search: String,
        page: Int? = 1
    ): BookSearch

    suspend fun getBookDetailInfo(
        path: Int
    ): BookDetailInfo

    suspend fun getBookDetailKeep(
        detail: Int
    ): BookEtc
}