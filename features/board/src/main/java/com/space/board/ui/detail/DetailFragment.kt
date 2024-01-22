package com.space.board.ui.detail

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.DividerItemDecoration
import com.space.core_ui.R

import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.shared.data.board.BoardDetailNum
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    private val detail by extraNotNull<String>("detail")
        .map { encodeString -> encodeString.decodeFromString<BoardDetailNum>() }

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: DetailViewModel by viewModels()


    override fun init() {
        super.init()
        detail.let { viewModel.getDetail(it) }
    }

    override fun initView() {
        super.initView()
        binding.titleToolbar.text = "게시판"
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                R.drawable.line_divider,
                5,
                5
            )
        )
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.detail.observe(viewLifecycleOwner) {
            val adapter = ConcatAdapter(
                DetailAdapter(it),
                CommentAdapter(it.commentDtoList)
            )
            binding.recyclerView.adapter = adapter
        }
    }

}