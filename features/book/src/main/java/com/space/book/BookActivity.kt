package com.space.book

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commitNow
import com.space.book.ui.home.BookHomeFragment
import com.space.core_ui.startActivity
import com.space.core_ui.startFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        if (savedInstanceState == null) {
            supportFragmentManager.commitNow {
                replace(R.id.container, BookHomeFragment.newInstance())
                setReorderingAllowed(true)
            }
        }
    }

    companion object {
        fun open(context: Context) {
            context.startActivity<BookActivity>()
        }
    }
}
