package com.space.board.ui.home

import androidx.fragment.app.viewModels
import com.space.board.R
import com.space.board.databinding.FragmentBoardContainerBinding
import com.space.core_ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardFragment :
    BaseFragment<FragmentBoardContainerBinding>(R.layout.fragment_board_container) {

    companion object {
        fun newInstance() = BoardFragment()
    }

    private val viewModel: BoardViewModel by viewModels()

    override fun init() {
        super.init()
    }

    override fun initListener() {
        super.initListener()
    }
}