package com.space.bible.ui

import androidx.fragment.app.viewModels
import com.space.core_ui.R
import com.space.bible.databinding.FragmentBibleContainerBinding
import com.space.core_ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class BibleFragment :
    BaseFragment<FragmentBibleContainerBinding>(R.layout.fragment_container) {

    companion object {
        fun newInstance() = BibleFragment()
    }

    private val viewModel: BibleViewModel by viewModels()

    override fun initView() {
        super.initView()
    }

    override fun initListener() {
        super.initListener()
    }


}