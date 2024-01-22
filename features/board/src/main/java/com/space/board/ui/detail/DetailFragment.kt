package com.space.board.ui.detail

import androidx.fragment.app.viewModels
import com.space.core_ui.R

import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: DetailViewModel by viewModels()


    override fun init() {
        super.init()
    }

    override fun initView() {
        super.initView()
        binding.titleToolbar.text = "게시판"
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initListener() {
        super.initListener()

    }

}