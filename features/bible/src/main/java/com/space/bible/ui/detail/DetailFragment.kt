package com.space.bible.ui.detail

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.bible.ui.adapter.BookInfoAdapter
import com.space.bible.ui.adapter.BookAdapter
import com.space.core_ui.base.ContainerFragment
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.shared.data.bible.BibleChapter
import com.space.shared.data.bible.BibleDetail
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : ContainerFragment<List<BibleChapter>>() {

    override val viewModel: DetailViewModel by viewModels()
    override val viewTitle: String = "성경"

    private val detail by extraNotNull<String>("detail").map {
        it.decodeFromString<BibleDetail>()
    }

    override fun init() = viewModel.setBible(detail)

    override fun initListener() {
        viewModel.view.observe(viewLifecycleOwner){
            val data = it.data ?: return@observe
            val adapter = ConcatAdapter(
                BookAdapter(detail),
                BookInfoAdapter(data)
            )
            binding.recyclerView.adapter = adapter

        }
    }

}