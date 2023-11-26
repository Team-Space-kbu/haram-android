package com.space.book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.book.ui.home.BookFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BookFragment.newInstance())
                .commitNow()
        }
    }

}
