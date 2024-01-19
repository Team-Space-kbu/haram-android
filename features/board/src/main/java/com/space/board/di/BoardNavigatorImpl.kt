package com.space.board.di

import android.content.Context
import com.space.board.ui.BoardActivity
import com.space.navigator.NavigatorBoard
import com.space.shared.data.board.BoardCategory
import javax.inject.Inject


internal class BoardNavigatorImpl @Inject constructor() : NavigatorBoard {
    override fun openBoard(context: Context, boardCategory: BoardCategory) {
        BoardActivity.open(context, boardCategory)
    }
}