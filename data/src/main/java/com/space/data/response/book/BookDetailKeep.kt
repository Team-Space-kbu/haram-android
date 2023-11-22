package com.space.data.response.book

import com.space.data.response.book.data.KeepBooksModel
import com.space.data.response.book.data.RelateBooksModel

data class BookDetailKeep(
    val keepBooks: KeepBooksModel,
    val relateBooks: RelateBooksModel
)
