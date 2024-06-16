package com.space.board.ui.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.space.core_ui.R
import com.space.core_ui.base.ContainerCustomFragment
import com.space.core_ui.binding.adapter.view.HeaderAdapter
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.shared.UiStatusType
import com.space.shared.data.board.BoardCategory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BoardFragment : ContainerCustomFragment<FragmentEmtpyContainerBinding, List<BoardCategory>>(
    R.layout.fragment_emtpy_container
) {
    companion object {
        fun newInstance() = BoardFragment()
    }

    override val viewModel: BoardViewModel by viewModels()


    override fun initView() {
        super.initView()
        if (viewModel.view.value?.uiUiStatusType == UiStatusType.LOADING) {
            binding.recyclerView.adapter = ShimmerAdapter()
        }else{
            binding.recyclerView.adapter = adapter
        }
    }

    override fun beforeSuccessListener() {
        super.beforeSuccessListener()
        val result = viewModel.view.value?.data ?: return
        adapter = ConcatAdapter(
            HeaderAdapter("학교 게시판"),
            CategoryAdapter(result) { category ->
                viewModel.boardNavigatorBoard.openView(requireContext(), category)
            }
        )
        binding.recyclerView.adapter = adapter
    }

}