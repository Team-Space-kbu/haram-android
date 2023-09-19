package com.space.data.res.book

import com.space.data.res.book.data.SearchResultModel

data class BookSearchReq(
    val start: Int,
    val end: Int,
    val result: List<SearchResultModel>
)
