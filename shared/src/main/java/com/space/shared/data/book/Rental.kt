package com.space.shared.data.book

data class Rental(
    val register: String,
    val number: String,
    val holdingInstitution: String,
    val loanStatus: String,
    val returnDate: String
)
