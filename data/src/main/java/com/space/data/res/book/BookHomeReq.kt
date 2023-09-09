package com.space.data.res.book

data class BookHomeReq(
    val image: List<String>,
    val newBook: List<CategoryModel>,
    val bestBook: List<CategoryModel>
)
