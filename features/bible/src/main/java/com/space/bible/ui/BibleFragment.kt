package com.space.bible.ui

import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.bible.BR
import com.space.bible.R
import com.space.bible.databinding.FragmentBibleContainerBinding
import com.space.bible.ui.adapter.ShimmerAdapter
import com.space.bible.ui.adapter.SliderAdapter
import com.space.bible.ui.adapter.TodayBibleAdapter
import com.space.bible.ui.adapter.TodayPrayAdapter
import com.space.bible.ui.detail.DetailFragment
import com.space.bible.ui.select.SelectFragment
import com.space.core_ui.base.ContainerCustomFragment
import com.space.core_ui.extension.transformFragment
import com.space.shared.data.bible.BibleInfo
import com.space.shared.data.bible.SelectorBible
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class BibleFragment : ContainerCustomFragment<FragmentBibleContainerBinding, BibleInfo>(
    R.layout.fragment_bible_container
) {

    companion object {
        fun newInstance() = BibleFragment()
    }

    override val viewModel: BibleViewModel by viewModels()

    private val click = object : ItemHandler {
        override fun onClick(selectorBible: SelectorBible) {
            parentFragmentManager.transformFragment<SelectFragment>(
                com.space.core_ui.R.id.container,
                "selector" to selectorBible.encodeToString()
            )
        }

        override fun onBible() {
            parentFragmentManager.transformFragment<DetailFragment>(
                com.space.core_ui.R.id.container,
                "detail" to viewModel.getBibleDetail().encodeToString()
            )
        }

    }

    override fun initView() {
        super.initView()
//        binding.setVariable(BR.title, "성경")
        binding.setVariable(BR.viewModel, viewModel)
        binding.setVariable(BR.itemHandler, click)
        binding.recyclerView.adapter = ShimmerAdapter()
    }

    override fun afterObserverListener() {
        setFragmentResultListener("bibleKey") { _, bundle ->
            val result = bundle.getString("chapter")
            val verse = bundle.getString("verse")
            result?.let {
                viewModel.setChapter(it)
            }
            verse?.let {
                viewModel.setVerse(it.replace("장", "").toInt())
            }
        }
        viewModel.view.observe(viewLifecycleOwner) {
            val data = it.data ?: return@observe
            binding.cardView.visibility = View.VISIBLE
            val adapter = ConcatAdapter(
//                HeaderAdapter("오늘의 성경말씀"),
                TodayBibleAdapter(data.bibleRandomVerse),
//                HeaderAdapter("공지사항"),
                SliderAdapter(data.bibleNoticeResponses),
//                HeaderAdapter("오늘의 기도"),
                TodayPrayAdapter()
            )
            binding.recyclerView.adapter = adapter
        }
    }

    interface ItemHandler {
        fun onClick(selectorBible: SelectorBible)

        fun onBible()
    }
}