package com.space.haram_android.common.data.response.book

data class BookHomeReq(
    val image: List<String>,
    val newBook: List<CategoryModel>,
    val bestBook: List<CategoryModel>
)
