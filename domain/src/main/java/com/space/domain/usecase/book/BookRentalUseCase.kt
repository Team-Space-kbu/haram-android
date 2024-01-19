package com.space.domain.usecase.book

import com.space.domain.base.UseCase
import com.space.repository.service.book.BookService
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