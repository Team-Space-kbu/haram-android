package com.space.data.response.book

import com.space.data.response.book.data.BookDetailInfo
import com.space.data.response.book.data.BookKeepInfo


data class BookDetailReq(
    val bookDetail: BookDetailInfo,
    val bookKeep: List<BookKeepInfo>
)