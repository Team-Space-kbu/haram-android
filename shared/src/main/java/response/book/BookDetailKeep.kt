package response.book

import response.book.data.KeepBooksModel
import response.book.data.RelateBooksModel

data class BookDetailKeep(
    val keepBooks: KeepBooksModel,
    val relateBooks: RelateBooksModel
)
