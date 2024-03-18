package com.space.board.ui.page

import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import com.space.board.databinding.FragmentBoardContainerBinding
import com.space.board.ui.detail.DetailFragment
import com.space.board.ui.wirte.WriteFragment
import com.space.core_ui.BR
import com.space.core_ui.R
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.showToast
import com.space.core_ui.transformFragment
import com.space.shared.UiStatusType
import com.space.shared.data.board.BoardCategory
import com.space.shared.data.board.BoardDetailNum
import com.space.shared.decodeFromString
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PageFragment : BaseFragment<FragmentBoardContainerBinding>(
    com.space.board.R.layout.fragment_board_container
) {

    private val page by extraNotNull<String>("page")
        .map { encodeString -> encodeString.decodeFromString<BoardCategory>() }

    companion object {
        fun newInstance() = PageFragment()
    }

    private val viewModel: PageViewModel by viewModels()

    override fun init() {
        super.init()
        page.let {
            viewModel.getPages(page.categorySeq)
        }
    }

    override fun initView() {
        binding.setVariable(BR.title, page.categoryName)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = ShimmerSearchAdapter()
    }

    override fun initListener() {
        binding.write.setOnClickListener {
            parentFragmentManager.transformFragment<WriteFragment>(
                R.id.container,
                "info" to page.encodeToString()
            )
        }
    }

    override fun afterObserverListener() {
        viewModel.category.observe(this) {
            if (it.uiUiStatusType == UiStatusType.SUCCESS) {
                val adapter =
                    CategoryAdapter(it.data!!.boards, it.data!!.categoryName) { boardPage ->
                        val detail = BoardDetailNum(it.data!!.categorySeq, boardPage.boardSeq)
                        parentFragmentManager.transformFragment<DetailFragment>(
                            R.id.container,
                            "detail" to detail.encodeToString()
                        )
                    }
                binding.recyclerView.adapter = adapter
                if (it.data!!.writeableBoard) {
                    binding.write.visibility = View.VISIBLE
                }
            }
        }
        setFragmentResultListener("updateUi") { _, bundle ->
            val result = bundle.getBoolean("event", false)
            if (result) {
                viewModel.getPages(page.categorySeq)
            }
        }
    }
}