package com.space.board.ui.home

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.R
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.base.ContainerCustomFragment
import com.space.core_ui.binding.adapter.view.HeaderAdapter
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.showToast
import com.space.shared.UiStatusType
import com.space.shared.data.board.BoardCategory
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class BoardFragment : ContainerCustomFragment<FragmentEmtpyContainerBinding, List<BoardCategory>>(
    R.layout.fragment_emtpy_container
) {
    companion object {
        fun newInstance() = BoardFragment()
    }


    override val viewModel: BoardViewModel by viewModels()
    private val categoryAdapter = CategoryAdapter(arrayListOf()) { boardCategory ->
        viewModel.boardNavigatorBoard.openView(requireContext(), boardCategory)
    }
    private val adapter =
        ConcatAdapter(
            HeaderAdapter("학교 게시판"),
            categoryAdapter
        )

    override fun initView() {
        binding.lifecycleOwner = viewLifecycleOwner
        if (viewModel.view.isInitialized) {
            binding.recyclerView.adapter = adapter
        } else {
            binding.recyclerView.adapter = ShimmerAdapter()
        }
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.view.observe(this) { result ->
            if (UiStatusType.SUCCESS == result.uiUiStatusType) {
                categoryAdapter.setList(result.data!!)
                binding.recyclerView.adapter = adapter
            }
        }
    }

}