package com.space.data.res.book

import com.space.data.res.book.data.CategoryModel

data class BookHomeReq(
    val image: List<String>,
    val newBook: List<CategoryModel>,
    val bestBook: List<CategoryModel>,
    val rentalBook: List<CategoryModel>
)
