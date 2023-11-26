package response.book.data

data class RelateBooksModel(
    val display: Int,
    val relatedBooks: List<CategoryModel>
)
