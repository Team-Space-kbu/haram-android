package com.space.navigator

import android.content.Context
import com.space.shared.data.board.BoardCategory

interface NavigatorBoard {
    fun openBoard(context: Context, boardCategory: BoardCategory)
}