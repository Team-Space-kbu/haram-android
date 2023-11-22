package com.space.data.response.book

import com.space.data.response.book.data.SearchResultModel

data class BookSearchReq(
    val start: Int,
    val end: Int,
    val result: List<SearchResultModel>
)
