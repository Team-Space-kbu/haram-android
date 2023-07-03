package com.space.data.response.book

data class BookHomeReq(
    val image: List<String>,
    val newBook: List<CategoryModel>,
    val bestBook: List<CategoryModel>
)
