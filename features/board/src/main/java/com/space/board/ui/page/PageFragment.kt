package com.space.board.ui.page

import androidx.fragment.app.viewModels
import com.space.board.ui.detail.DetailFragment
import com.space.core_ui.BR
import com.space.core_ui.R
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.transformFragment
import com.space.shared.data.board.BoardCategory
import com.space.shared.data.board.BoardDetailNum
import com.space.shared.decodeFromString
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PageFragment : BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    private val page by extraNotNull<String>("page")
        .map { encodeString -> encodeString.decodeFromString<BoardCategory>() }

    companion object {
        fun newInstance() = PageFragment()
    }

    private val viewModel: PageViewModel by viewModels()

    override fun init() {
        super.init()
        page.let { viewModel.getPages(page.boardType) }
    }

    override fun initView() {
        super.initView()
        binding.setVariable(BR.title,"게시판")
        binding.lifecycleOwner = viewLifecycleOwner

    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.category.observe(viewLifecycleOwner) {
            val adapter = CategoryAdapter(it) { boardPage ->
                val detail = BoardDetailNum(boardPage.boardSeq.toString(), page.boardType)
                parentFragmentManager.transformFragment<DetailFragment>(
                    R.id.container,
                    "detail" to detail.encodeToString()
                )
            }
            binding.recyclerView.adapter = adapter
        }
    }


}