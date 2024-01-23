package com.space.navigator.view

import android.content.Context
import com.space.shared.data.board.BoardCategory

interface NavigatorBoard {
    fun openView(context: Context, boardCategory: BoardCategory)
}