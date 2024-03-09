package com.space.bible.ui

import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.bible.BR
import com.space.bible.R
import com.space.bible.databinding.FragmentBibleContainerBinding
import com.space.bible.ui.adapter.ShimmerAdapter
import com.space.core_ui.view.adapter.HeaderAdapter
import com.space.bible.ui.adapter.SliderAdapter
import com.space.bible.ui.adapter.TodayBibleAdapter
import com.space.bible.ui.adapter.TodayPrayAdapter
import com.space.bible.ui.detail.DetailFragment
import com.space.bible.ui.select.SelectFragment
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.transformFragment
import com.space.shared.data.bible.SelectorBible
import com.space.shared.encodeToString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
internal class BibleFragment :
    BaseFragment<FragmentBibleContainerBinding>(R.layout.fragment_bible_container) {

    companion object {
        fun newInstance() = BibleFragment()
    }

    private val viewModel: BibleViewModel by viewModels()

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
        binding.setVariable(BR.viewModel, viewModel)
        binding.setVariable(BR.itemHandler, click)
        binding.titleToolbar.text = "성경"
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = ShimmerAdapter()
    }


    override fun initListener() {
        super.initListener()
        viewModel.bibleInfo.observe(viewLifecycleOwner) {
            binding.cardView.visibility = View.VISIBLE
            val adapter = ConcatAdapter(
                HeaderAdapter("오늘의 성경말씀"),
                TodayBibleAdapter(it.bibleRandomVerse),
                HeaderAdapter("공지사항"),
                SliderAdapter(it.bibleNoticeResponses),
                HeaderAdapter("오늘의 기도"),
                TodayPrayAdapter()
            )
            binding.recyclerView.adapter = adapter
        }

    }

    override fun afterObserverListener() {
        super.afterObserverListener()
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
    }

    interface ItemHandler {
        fun onClick(selectorBible: SelectorBible)

        fun onBible()
    }
}