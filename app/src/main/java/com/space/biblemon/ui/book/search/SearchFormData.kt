package com.space.biblemon.ui.book.search

import response.book.BookSearchReq

/**
 * Data validation state of the login form.
 */
data class SearchFormData(
    val searchReq: BookSearchReq? = null,
    val searchData: Boolean = false

)