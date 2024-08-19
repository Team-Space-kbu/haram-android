package com.space.bible.ui.select

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.space.bible.ui.adapter.SelectAdapter
import com.space.core_ui.R
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.extension.extraNotNull
import com.space.core_ui.extension.map
import com.space.shared.data.bible.SelectorBible
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectFragment :
    BaseFragment<FragmentEmtpyContainerBinding>(R.layout.fragment_emtpy_container) {

    private val selectorBible by extraNotNull<String>("selector").map {
        it.decodeFromString<SelectorBible>()
    }

    override fun initView() {
        val data: SelectorBible = selectorBible
        val adapter = SelectAdapter(
            if (data.status) {
                List(data.verse) { "${it + 1}장" }
            } else {
                data.chapter!!
            }
        ) { text ->
            if (data.status) {
                setFragmentResult("bibleKey", bundleOf("verse" to text))
            } else {
                setFragmentResult("bibleKey", bundleOf("chapter" to text))
            }
            parentFragmentManager.popBackStack()
        }
        binding.recyclerView.adapter = adapter
    }
}