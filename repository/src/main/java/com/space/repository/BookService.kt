package com.space.repository

import com.space.shared.data.book.BookDetail
import com.space.shared.data.book.BookKeep
import com.space.shared.data.book.BookHome
import com.space.shared.data.book.BookSearch

interface BookService {
    suspend fun getBookHome(): BookHome

    suspend fun getBokSearch(
        search: String,
        page: Int? = null
    ): BookSearch

    suspend fun getBookDetailInfo(): BookDetail

    suspend fun getBookDetailKeep(
        detail: Int
    ): BookKeep
}