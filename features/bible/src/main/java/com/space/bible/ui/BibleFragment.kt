package com.space.bible.ui

import android.view.View
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import com.space.bible.BR
import com.space.bible.R
import com.space.bible.databinding.FragmentBibleContainerBinding
import com.space.core_ui.adapter.HeaderAdapter
import com.space.bible.ui.adapter.SliderAdapter
import com.space.bible.ui.adapter.TodayBibleAdapter
import com.space.bible.ui.adapter.TodayPrayAdapter
import com.space.bible.ui.detail.DetailFragment
import com.space.bible.ui.select.SelectFragment
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.transformFragment
import com.space.shared.data.bible.BibleDetail
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


    override fun initView() {
        super.initView()
        binding.setVariable(BR.viewModel, viewModel)
        binding.titleToolbar.text = "성경"
        binding.lifecycleOwner = viewLifecycleOwner
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
        binding.chapter.setOnClickListener {
            val selectorBible = SelectorBible(status = false, viewModel.bible.keys.toList())
            onFragment(selectorBible)

        }
        binding.verse.setOnClickListener {
            val chapter = binding.chapter.text
            val selectorBible = SelectorBible(status = true, verse = viewModel.bible[chapter]!!.toInt())
            onFragment(selectorBible)
        }
        binding.findBible.setOnClickListener {
            val bibleDetail = BibleDetail(
                chapter = viewModel.chapter.value!!,
                verse = viewModel.verse.value!!
            )
            parentFragmentManager.transformFragment<DetailFragment>(
                com.space.core_ui.R.id.container,
                "detail" to bibleDetail.encodeToString()
            )
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

    private fun onFragment(selectorBible: SelectorBible) {
        parentFragmentManager.transformFragment<SelectFragment>(
            com.space.core_ui.R.id.container,
            "selector" to selectorBible.encodeToString()
        )
    }
}