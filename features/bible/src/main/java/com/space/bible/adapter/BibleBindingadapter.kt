@file:Suppress("SetTextI18n")

package com.space.bible.adapter

import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.space.shared.data.bible.BibleChapter

@BindingAdapter("bibleInfo")
fun setBibleInfo(
    textView: TextView,
    bibleChapter: BibleChapter
) {
    textView.text = "${bibleChapter.bookName}, ${bibleChapter.chapter}장 ${bibleChapter.verse}절"
}

@BindingAdapter("setVerse")
fun setVerse(
    textView: TextView,
    verse: String
) {
    textView.text = "${verse}장"
}

@BindingAdapter("setBookName")
fun setBookName(
    textView: TextView,
    bookName: String
) {
    textView.text = bookName
}

@BindingAdapter("onBook")
fun onBookName(
    textView: TextView,
    bookName: String
) {
    textView.text = bookName
}