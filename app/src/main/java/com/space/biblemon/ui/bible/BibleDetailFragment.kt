package com.space.biblemon.ui.bible

import androidx.fragment.app.viewModels
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentBibleDetailBinding

class BibleDetailFragment : BaseFragment<FragmentBibleDetailBinding>(R.layout.fragment_bible_detail) {

    companion object {
        fun newInstance() = BibleDetailFragment()
    }

    private val viewModel: BibleDetailViewModel by viewModels()

}