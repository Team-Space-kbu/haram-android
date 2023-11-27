package com.space.book.ui.detail

import com.space.book.R
import com.space.book.databinding.FragmentContainerBinding
import com.space.core_ui.base.BaseFragment

class DetailFragment : BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private lateinit var viewModel: DetailViewModel


}