package com.space.domain.book

import com.space.domain.UseCase
import com.space.data.service.book.BookService
import com.space.shared.annotation.IoDispatcher
import com.space.shared.data.book.BookDetailInfo
import com.space.shared.data.book.Category
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class BookDetailUseCase @Inject constructor(
    private val bookService: BookService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : UseCase<Category, BookDetailInfo>(dispatcher) {
    override suspend fun execute(param: Category): BookDetailInfo {
        return bookService.getBookDetailInfo(param.path)
    }
}