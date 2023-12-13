package com.space.repository.service.impl

import com.space.repository.service.inf.BookService
import com.space.repository.api.BookApi
import com.space.shared.data.book.BookDetailInfo
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

    override suspend fun getBookSearch(search: String, page: Int?): BookSearch {
        return runBlocking {
            bookApi.getBokSearch(search, page).data
        }
    }

    override suspend fun getBookDetailInfo(path: Int): BookDetailInfo {
        return runBlocking {
            bookApi.getBookDetailInfo(path).data
        }
    }

    override suspend fun getBookDetailKeep(detail: Int): BookKeep {
        return runBlocking {
            bookApi.getBookDetailKeep(detail).data
        }
    }
}