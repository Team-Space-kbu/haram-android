package com.space.board.ui.detail

import androidx.fragment.app.viewModels
import com.space.board.R
import com.space.board.databinding.FragmentBoardContainerBinding
import com.space.core_ui.base.BaseFragment

class DetailFragment : BaseFragment<FragmentBoardContainerBinding>(R.layout.fragment_board_container) {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: DetailViewModel by viewModels()


    override fun init() {
        super.init()
    }

    override fun initListener() {
        super.initListener()
    }

}