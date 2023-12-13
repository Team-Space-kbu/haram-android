package com.space.bible.ui.select

import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import com.space.bible.ui.adapter.SelectAdapter
import com.space.core_ui.R
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentEmtpyContainerBinding
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.shared.data.bible.SelectorBible
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectFragment :
    BaseFragment<FragmentEmtpyContainerBinding>(R.layout.fragment_emtpy_container) {

    private val selectorBible by extraNotNull<String>("selector").map {
        it.decodeFromString<SelectorBible>()
    }

    private val click = object : SelectAdapter.ItemHandler {
        override fun clickSelect(string: String) {
            if (selectorBible.status) {
                setFragmentResult("bibleKey", bundleOf("verse" to string))
            } else {
                setFragmentResult("bibleKey", bundleOf("chapter" to string))
            }
            parentFragmentManager.popBackStack()
        }
    }

    override fun initView() {
        super.initView()
        val adapter = SelectAdapter(
            if (selectorBible.status) {
                createList(selectorBible.verse!!)
            } else {
                selectorBible.chapter!!
            },
            click
        )
        binding.recyclerView.adapter = adapter
    }

    private fun createList(size: Int): List<String> {
        return List(size) { "${it + 1}장" }
    }

}