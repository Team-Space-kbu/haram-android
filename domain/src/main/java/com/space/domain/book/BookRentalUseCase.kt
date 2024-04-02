package com.space.domain.book

import com.space.domain.UseCase
import com.space.data.service.book.BookService
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.book.BookEtc
import com.space.shared.data.book.Category
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class BookRentalUseCase @Inject constructor(
    private val bookService: BookService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : UseCase<Category, BookEtc>(dispatcher) {
    override suspend fun execute(param: Category): BookEtc {
        return bookService.getBookDetailKeep(param.path)
    }
}