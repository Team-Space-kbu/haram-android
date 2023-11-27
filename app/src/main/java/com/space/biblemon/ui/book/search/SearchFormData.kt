package com.space.biblemon.ui.book.search

import com.space.shared.data.book.BookSearch

/**
 * Data validation state of the login form.
 */
data class SearchFormData(
    val searchReq: BookSearch? = null,
    val searchData: Boolean = false

)