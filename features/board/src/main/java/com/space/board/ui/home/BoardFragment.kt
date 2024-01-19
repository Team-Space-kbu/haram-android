package com.space.board.ui.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.R
import com.space.board.databinding.FragmentBoardContainerBinding
import com.space.core_ui.adapter.HeaderAdapter
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.transformFragment
import com.space.navigator.NavigatorBoard
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class BoardFragment :
    BaseFragment<FragmentEmtpyContainerBinding>(R.layout.fragment_emtpy_container) {

    companion object {
        fun newInstance() = BoardFragment()
    }

    @Inject
    lateinit var boardNavigatorBoard: NavigatorBoard
    private val viewModel: BoardViewModel by viewModels()

    override fun initView() {
        super.initView()
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun initListener() {
        super.initListener()
        viewModel.category.observe(viewLifecycleOwner) {
            val adapter = ConcatAdapter(
                HeaderAdapter("학교 게시판"),
                CategoryAdapter(it) { boardCategory ->
                    boardNavigatorBoard.openBoard(requireContext(), boardCategory)
                }
            )
            binding.recyclerView.adapter = adapter
        }
    }
}