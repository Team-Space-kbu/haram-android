package com.space.haram_android.common.data.response.book

import com.space.haram_android.common.data.response.book.data.BookDetailInfo
import com.space.haram_android.common.data.response.book.data.BookKeepInfo

data class BookDetailReq(
    val bookInfoRes: BookDetailInfo,
    val bookKeep: BookKeepInfo
)