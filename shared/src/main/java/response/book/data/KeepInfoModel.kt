package response.book.data

data class KeepInfoModel(
    val register: String,
    val number: String,
    val holdingInstitution: String,
    val loanStatus: String,
    val returnDate: String
)