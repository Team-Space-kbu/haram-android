package com.space.board.ui.page

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.space.board.R
import com.space.board.databinding.FragmentBoardContainerBinding
import com.space.core_ui.base.BaseFragment

class PageFragment :
    BaseFragment<FragmentBoardContainerBinding>(R.layout.fragment_board_container) {

    companion object {
        fun newInstance() = PageFragment()
    }

    private val viewModel: PageViewModel by viewModels()

    override fun init() {
        super.init()
    }

    override fun initListener() {
        super.initListener()
    }


}