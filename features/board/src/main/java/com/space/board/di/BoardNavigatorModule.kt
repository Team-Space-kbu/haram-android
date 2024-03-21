package com.space.board.di

import android.content.Context
import com.space.board.BoardActivity
import com.space.navigator.view.NavigatorBoard
import com.space.shared.data.board.BoardCategory
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
internal abstract class BoardNavigatorModule {

    @Binds
    abstract fun providerBoardNavigator(
        navigatorBoard: BoardNavigatorImpl
    ): NavigatorBoard

}

internal class BoardNavigatorImpl @Inject constructor() : NavigatorBoard {
    override fun openView(context: Context, boardCategory: BoardCategory) {
        BoardActivity.open(context, boardCategory)
    }
}


