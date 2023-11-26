package response.book

import response.book.data.SearchResultModel

data class BookSearchReq(
    val start: Int,
    val end: Int,
    val result: List<SearchResultModel>
)
