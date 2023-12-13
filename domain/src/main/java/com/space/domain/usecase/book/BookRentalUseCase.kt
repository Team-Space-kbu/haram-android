package com.space.domain.usecase.book

import com.space.domain.base.UseCase
import com.space.repository.service.inf.BookService
import com.space.shared.common.annotation.IoDispatcher
import com.space.shared.data.book.BookKeep
import com.space.shared.data.book.Category
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class BookRentalUseCase @Inject constructor(
    private val bookService: BookService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : UseCase<Category, BookKeep>(dispatcher) {
    override suspend fun execute(param: Category): BookKeep {
        return bookService.getBookDetailKeep(param.path)
    }
}