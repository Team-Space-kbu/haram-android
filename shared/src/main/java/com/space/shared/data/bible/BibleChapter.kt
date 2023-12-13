package com.space.shared.data.bible

import com.google.gson.annotations.SerializedName

data class BibleChapter(
    @SerializedName(value = "bookName", alternate = ["book"])
    val bookName: String,
    val chapter: Int,
    val verse: Int,
    val content: String,
)
