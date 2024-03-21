package com.space.board

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.space.board.ui.page.PageFragment
import com.space.core_ui.startActivity
import com.space.core_ui.R
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.startFragment
import com.space.shared.data.board.BoardCategory
import com.space.shared.decodeFromString
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardActivity : AppCompatActivity() {
    companion object {
        fun open(context: Context, boardCategory: BoardCategory) {
            context.startActivity<BoardActivity>(
                "category" to boardCategory.encodeToString()
            )
        }
    }

    private val category by extraNotNull<String>("category")
        .map { encodeString ->
            encodeString.decodeFromString<BoardCategory>()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_container)
        if (savedInstanceState == null) {
            supportFragmentManager.startFragment<PageFragment>(
                R.id.container,
                "page" to category.encodeToString()
            )
        }
    }


}
