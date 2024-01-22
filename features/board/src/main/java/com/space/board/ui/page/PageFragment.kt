package com.space.board.ui.page

import androidx.fragment.app.viewModels
import com.space.board.ui.detail.DetailFragment
import com.space.core_ui.R
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.core_ui.transformFragment
import com.space.shared.data.board.BoardCategory
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

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
        page.let {
            viewModel.getPages(page.boardType)
        }
    }

    override fun initView() {
        super.initView()
        binding.titleToolbar.text = "게시판"
        binding.lifecycleOwner = viewLifecycleOwner
    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.category.observe(viewLifecycleOwner) {
            val adapter = CategoryAdapter(it) { page ->
                parentFragmentManager.transformFragment<DetailFragment>(
                    R.id.container,
                    "detail" to page.boardSeq
                )
            }
            binding.recyclerView.adapter = adapter
        }
    }


}