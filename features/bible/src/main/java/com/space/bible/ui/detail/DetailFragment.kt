package com.space.bible.ui.detail

import androidx.fragment.app.viewModels
import com.space.core_ui.R
import com.space.core_ui.base.BaseFragment
import com.space.core_ui.databinding.FragmentContainerBinding
import com.space.core_ui.extraNotNull
import com.space.core_ui.map
import com.space.shared.data.bible.BibleDetail
import com.space.shared.data.bible.SelectorBible
import com.space.shared.decodeFromString
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentContainerBinding>(R.layout.fragment_container) {

    private val viewModel: DetailViewModel by viewModels()

    private val detail by extraNotNull<String>("detail").map {
        it.decodeFromString<BibleDetail>()
    }

    override fun initView() {
        super.initView()

    }

    override fun initListener() {
        super.initListener()
    }

}