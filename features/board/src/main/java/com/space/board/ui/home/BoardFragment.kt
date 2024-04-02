package com.space.board.ui.home

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.R
import com.space.core_ui.binding.adapter.view.HeaderAdapter
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.navigator.view.NavigatorBoard
import com.space.shared.UiStatusType
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
    private val categoryAdapter = CategoryAdapter(arrayListOf()) { boardCategory ->
        boardNavigatorBoard.openView(requireContext(), boardCategory)
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

    override fun initListener() {
        viewModel.view.observe(viewLifecycleOwner) { result ->
            when (result.uiUiStatusType) {
                UiStatusType.SUCCESS -> {
                    categoryAdapter.setList(result.data!!)
                    binding.recyclerView.adapter = adapter
                }
                UiStatusType.LOGOUT ->{
                    activity?.finishAffinity()
                    viewModel.navigatorLogin.openView(requireContext())
                }

                UiStatusType.NO_CONNECTION -> {
                    Toast.makeText(context, "인터넷 연결상태가 좋지 않아 연결할 수 없습니다.", Toast.LENGTH_LONG).show()
                }

                else -> {
                    Toast.makeText(context, "알 수 없는 오류가 발생했습니다.", Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}