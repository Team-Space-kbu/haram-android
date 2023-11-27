package com.space.repository.service

import com.space.repository.BookService
import com.space.repository.api.BookApi
import com.space.shared.data.book.BookDetail
import com.space.shared.data.book.BookHome
import com.space.shared.data.book.BookKeep
import com.space.shared.data.book.BookSearch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

internal class BookServiceImpl @Inject constructor(
    private val bookApi: BookApi
) : BookService {
    override suspend fun getBookHome(): BookHome {
        return runBlocking {
            bookApi.getBookHome().data
        }
    }

    override suspend fun getBokSearch(search: String, page: Int?): BookSearch {
        TODO("Not yet implemented")
    }

    override suspend fun getBookDetailInfo(): BookDetail {
        TODO("Not yet implemented")
    }

    override suspend fun getBookDetailKeep(detail: Int): BookKeep {
        TODO("Not yet implemented")
    }
}