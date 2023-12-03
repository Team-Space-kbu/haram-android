package com.space.domain.usecase.book

import com.space.domain.base.NonParamUseCase
import com.space.repository.BookService
import com.space.shared.annotation.IoDispatcher
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