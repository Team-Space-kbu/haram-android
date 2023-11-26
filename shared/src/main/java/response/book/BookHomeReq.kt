package response.book

import response.book.data.CategoryModel

data class BookHomeReq(
    val image: List<String>,
    val newBook: List<CategoryModel>,
    val bestBook: List<CategoryModel>,
    val rentalBook: List<CategoryModel>
)
