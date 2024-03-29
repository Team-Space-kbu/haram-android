package com.space.shared.data.book

import kotlinx.serialization.Serializable

@Serializable
data class Category(
    val path: Int,
    val image: String,
    val title: String
) {
    companion object {
        fun searchToCategory(search: Search) = Category(
            path = search.path,
            image = search.image,
            title = search.title
        )
    }
}
