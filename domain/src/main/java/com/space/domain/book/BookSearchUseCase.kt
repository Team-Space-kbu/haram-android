package com.space.domain.book

import com.space.domain.UseCase
import com.space.data.service.book.BookService
import com.space.shared.annotation.IoDispatcher
import com.space.shared.data.book.BookSearch
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class BookSearchUseCase @Inject constructor(
    private val bookService: BookService,
    @IoDispatcher dispatcher: CoroutineDispatcher,
) : UseCase<BookSearchUseCase.SearchParam, BookSearch>(dispatcher) {
    override suspend fun execute(param: SearchParam): BookSearch {
        return bookService.getBookSearch(
            search = param.text,
            page = param.page

        )
    }

    data class SearchParam(
        val text: String,
        val page: Int? = 1
    )
}