package com.space.bible.ui.detail

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.bible.ui.adapter.BookInfoAdapter
import com.space.bible.ui.adapter.BookAdapter
import com.space.core_ui.BR
import com.space.core_ui.R
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.shared.data.bible.BibleDetail
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    private val viewModel: DetailViewModel by viewModels()

    private val detail by extraNotNull<String>("detail").map {
        it.decodeFromString<BibleDetail>()
    }

    override fun init() {
        super.init()
        viewModel.setBible(detail)
    }

    override fun initView() {
        super.initView()
        binding.setVariable(BR.title,"성경")
        binding.lifecycleOwner = viewLifecycleOwner

    }

    override fun initListener() {
        super.initListener()
        viewModel.bibleInfo.observe(viewLifecycleOwner){
            val adapter = ConcatAdapter(
                BookAdapter(detail),
                BookInfoAdapter(it)
            )
            binding.recyclerView.adapter = adapter
        }
    }

}