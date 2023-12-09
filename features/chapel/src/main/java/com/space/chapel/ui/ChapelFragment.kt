package com.space.chapel.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.chapel.R
import com.space.chapel.databinding.FragmentChapelContainerBinding
import com.space.chapel.ui.databinding.adapter.ChapelDetailAdapter
import com.space.chapel.ui.databinding.adapter.ChapelInfoAdapter
import com.space.chapel.ui.databinding.adapter.ChapelInfoDetailAdapter
import com.space.chapel.ui.databinding.adapter.HeaderAdapter
import com.space.core_ui.DividerItemDecoration
import com.space.core_ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ChapelFragment :
    BaseFragment<FragmentChapelContainerBinding>(R.layout.fragment_chapel_container) {

    companion object {
        fun newInstance() = ChapelFragment()
    }

    private val viewModel: ChapelViewModel by viewModels()

    override fun initView() {
        super.initView()
        binding.titleToolbar.text = "채플조회"
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initListener() {
        super.initListener()
        viewModel.chapelInfo.observe(viewLifecycleOwner) {
            val adapter = ConcatAdapter(
                ChapelInfoAdapter(it.chapelInfo),
                ChapelInfoDetailAdapter(it.chapelInfo),
                HeaderAdapter(),
                ChapelDetailAdapter(it.chapelDetail)
            )
            binding.recyclerView.adapter = adapter
        }
    }
}