package com.space.board.ui.page

import android.app.Activity.RESULT_OK
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.space.board.databinding.FragmentBoardContainerBinding
import com.space.board.ui.detail.DetailFragment
import com.space.board.ui.wirte.WriteFragment
import com.space.board.BR
import com.space.core_ui.NonParamsItemHandler
import com.space.core_ui.R
import com.space.core_ui.base.ContainerCustomFragment
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.showToast
import com.space.core_ui.transformFragment
import com.space.shared.UiStatusType
import com.space.shared.data.board.BoardCategory
import com.space.shared.data.board.BoardDetailNum
import com.space.shared.data.board.BoardPage
import com.space.shared.decodeFromString
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PageFragment : ContainerCustomFragment<FragmentBoardContainerBinding, BoardPage>(
    com.space.board.R.layout.fragment_board_container
) {

    private val page by extraNotNull<String>("page")
        .map { it.decodeFromString<BoardCategory>() }

    companion object {
        fun newInstance() = PageFragment()
    }
    override val viewModel: PageViewModel by viewModels()

    private var status: Boolean = false
    private val shimmer = ShimmerSearchAdapter()
    private val categoryAdapter = CategoryAdapter { boardPage ->
        val detail = BoardDetailNum(
            page.categorySeq,
            boardPage.boardSeq,
            page.writeableAnonymous
        )
        parentFragmentManager.transformFragment<DetailFragment>(
            R.id.container,
            "detail" to detail.encodeToString()
        )
    }

    override fun init() {
        viewModel.getPages(page.categorySeq)
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val name = result.data?.getBooleanExtra("status", false) ?: false
                if (name) {
                    viewModel.getPages(page.categorySeq)
                }
            }
        }
    }

    override fun initView() {
        binding.setVariable(BR.title, page.categoryName)
        binding.setVariable(
            BR.writeHandler,
            NonParamsItemHandler {
                parentFragmentManager.transformFragment<WriteFragment>(
                    R.id.container,
                    "info" to page.encodeToString()
                )
            }
        )
        if (!viewModel.view.isInitialized) {
            binding.recyclerView.adapter = shimmer
        } else {
            binding.recyclerView.adapter = categoryAdapter
        }
    }

    override fun initListener() {
        setFragmentResultListener("updateUi") { _, bundle ->
            if (bundle.getBoolean("event", false)) {
                viewModel.getPages(page.categorySeq)
                categoryAdapter.clearCategories()
                requireContext().showToast("게시글이 삭제되었습니다.")
            }
        }
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, state: Int) {
                if (!binding.recyclerView.canScrollVertically(2) && state == RecyclerView.SCROLL_STATE_IDLE) {
                    val data = viewModel.view.value?.data ?: return
                    val index = data.startPage + 1
                    if (index <= data.endPage && !status) {
                        requireContext().showToast("게시글을 더 불러옵니다.")
                        status = true
                        viewModel.getPages(page.categorySeq, index)
                    }
                }
            }
        })
    }

    override fun beforeObserverListener() {
        super.beforeObserverListener()
        viewModel.view.observe(this) { boardPage ->
            if (boardPage.uiUiStatusType == UiStatusType.SUCCESS) {
                val data = boardPage.data ?: return@observe
                categoryAdapter.addCategories(data.boards)
            }
        }
    }

    override fun afterObserverListener() {
        viewModel.view.observe(this) { boardPage ->
            if (boardPage.uiUiStatusType == UiStatusType.SUCCESS) {
                val data = boardPage.data ?: return@observe
                if (data.startPage <= 1) {
                    categoryAdapter.setBoard(data.categoryName)
                    binding.recyclerView.adapter = categoryAdapter
                }
                if (data.writeableBoard) {
                    binding.write.visibility = View.VISIBLE
                }
            }
        }
    }
}
