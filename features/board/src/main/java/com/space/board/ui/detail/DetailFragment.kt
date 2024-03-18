package com.space.board.ui.detail

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.core_ui.BR
import com.space.core_ui.DividerItemDecoration
import com.space.core_ui.R

import com.space.core_ui.base.BaseFragment
import com.space.core_ui.binding.adapter.ImageSliderAdapter
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
        binding.setVariable(BR.title, "게시판")
        binding.lifecycleOwner = viewLifecycleOwner

    }

    override fun afterObserverListener() {
        super.afterObserverListener()
        viewModel.detail.observe(viewLifecycleOwner) { detail ->
            val image = detail.files.map { it.fileUrl }.toList()
            val adapter = ConcatAdapter(
                DetailAdapter(detail),
                ImageSliderAdapter(image, image.isNotEmpty()) {

                },
                LineAdapter(),
                CommentAdapter(detail.comments)
            )
            binding.recyclerView.adapter = adapter
        }
    }

}