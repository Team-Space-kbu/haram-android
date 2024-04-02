package com.space.domain.book

import com.space.domain.NonParamUseCase
import com.space.data.service.book.BookService
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.book.BookHome
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class BookHomeUseCase @Inject constructor(
    private val bookService: BookService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : NonParamUseCase<BookHome>(dispatcher) {
    override suspend fun execute(): BookHome {
        return bookService.getBookHome()
    }
}