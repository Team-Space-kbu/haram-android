package com.space.biblemon.ui.bible


import androidx.fragment.app.viewModels
import com.space.biblemon.R
import com.space.biblemon.base.view.BaseFragment
import com.space.biblemon.databinding.FragmentBibleBinding

class BibleFragment : BaseFragment<FragmentBibleBinding>(R.layout.fragment_bible) {

    companion object {
        fun newInstance() = BibleFragment()
    }

    private val viewModel: BibleViewModel by viewModels()


}